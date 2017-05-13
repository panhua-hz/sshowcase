package controller;

import static tools.WebHandler.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dataservices.RfaPrjService;
import domain.EntRfaPrj;
import domain.LinkVO;
import tools.MyJasperRptWrapper;


@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping
	public String home(Model model){
		logger.info("into HomeController.home");
		
		List<LinkVO> linkVOList = new ArrayList<LinkVO>();
		linkVOList.add(new LinkVO("Show HelloXML","/helloXml"));
		linkVOList.add(new LinkVO("PrjRpt by DataSource","/prjbyds.html"));
		linkVOList.add(new LinkVO("PrjRpt output stream","/prj2.html"));
		linkVOList.add(new LinkVO("PrjRpt by viewResolver","/prj.html"));
		
		for (LinkVO linkVO : linkVOList){
			logger.info(linkVO.toString());
		}
		
		model.addAttribute(linkVOList);
		
		return "home";
	}
	
	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value = "prjbyds.{type}", method = GET)
	public void prjRptByDataSource(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
		logger.info("prjRptByDataSource type is " + type);
		String jasperFile = request.getSession().getServletContext().getRealPath("/jasper/rpt_prj.jasper");
		String imgDir = request.getSession().getServletContext().getRealPath("/image/")+"/";
		String baseUrl = request.getSession().getServletContext().getContextPath()+"/image"+"\\";
		
		logger.info("IMAGES_DIR_NAME:imgDir->"+imgDir);
		logger.info("IMAGES_URI:baseUrl->"+baseUrl);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("baseUrl", imgDir);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("SQLException: ",e);
		}
		MyJasperRptWrapper wrp = new MyJasperRptWrapper(jasperFile, params, conn);
		wrp.setImg(baseUrl, imgDir);

		byte[] result = null;
		if ("xls".equalsIgnoreCase(type)) {
			result = wrp.exportAsXls();
			writeResponseExlData(response, result);
		} else if ("pdf".equalsIgnoreCase(type)) {
			result = wrp.exportAsPDF();
			writeResponsePdf(response, result);
		} else {
			result = wrp.exportAsHtml();
			writeResponseHtml(response, result);
		}
	}
	
	@Autowired
	RfaPrjService rfaPrjService;
	
	@RequestMapping(value = "prj2.{type}", method = GET)
	public void prjRpt2(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
		String jasperFile = request.getSession().getServletContext().getRealPath("/jasper/rpt_prj.jasper");
		String imgDir = request.getSession().getServletContext().getRealPath("/image/")+"/";
		String baseUrl = request.getSession().getServletContext().getContextPath()+"/image"+"\\";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("baseUrl", imgDir);
		
		List<EntRfaPrj> prjList = this.rfaPrjService.getRfaPrj();
		MyJasperRptWrapper wrp = new MyJasperRptWrapper(jasperFile, params, prjList);
		wrp.setImg(baseUrl, imgDir);

		byte[] result = null;
		if ("xls".equalsIgnoreCase(type)) {
			result = wrp.exportAsXls();
			writeResponseExlData(response, result);
		} else if ("pdf".equalsIgnoreCase(type)) {
			result = wrp.exportAsPDF();
			writeResponsePdf(response, result);
		} else {
			result = wrp.exportAsHtml();
			writeResponseHtml(response, result);
		}
	}
	
	@RequestMapping(value = "prj.{type}", method = GET)
	public String prjRpt(@PathVariable String type, HttpServletRequest request, Model model){
		type = "xls".equalsIgnoreCase(type) || "pdf".equalsIgnoreCase(type)?type:"html";
		String baseUrl = request.getSession().getServletContext().getRealPath("/image")+"\\";
		logger.info("baseUrl->"+baseUrl);
		List<EntRfaPrj> prjList = this.rfaPrjService.getRfaPrj();
		model.addAttribute("baseUrl",baseUrl);		
		model.addAttribute("dataList", prjList);
		model.addAttribute("format",type);
		
		return "rpt_prj";
	}
	
	@RequestMapping(value = "helloXml")
	public void helloXml(HttpServletRequest request, HttpServletResponse response){
		String LINE_SEPERATOR = System.lineSeparator();
		String output="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+LINE_SEPERATOR
                + "<inventory>"+LINE_SEPERATOR
                + "\t<book year=\"2000\">"+LINE_SEPERATOR
                + "\t\t<title>Burning Tower</title>"+LINE_SEPERATOR
                + "\t\t<author>Larry Niven</author>"+LINE_SEPERATOR
                + "\t\t<author>Jerry Pournelle</author>"+LINE_SEPERATOR
                + "\t\t<publisher>Pocket</publisher>"+LINE_SEPERATOR
                + "\t\t<isbn>0743416910</isbn>"+LINE_SEPERATOR
                + "\t\t<price>5.99</price>"+LINE_SEPERATOR
                + "\t</book>"+LINE_SEPERATOR
                + "</inventory>";
		writeResponseXml(response, output);
	}
}
