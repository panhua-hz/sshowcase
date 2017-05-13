package tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import exceptions.SSRptException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class MyJasperRptWrapper {
	String jsperFullName;
	InputStream jsperInputStream;
	Map<String, Object> inParams;
	Connection dsConn;
	List<?> dsList;
	
	String imgUrl;
	String imgDir;
	
	public MyJasperRptWrapper(String jsperFullName, Map<String, Object> inParams, Connection dsConn){
		this.jsperFullName = jsperFullName;
		this.inParams = inParams;
		this.dsConn = dsConn;
	}
	public MyJasperRptWrapper(InputStream jsperInputStream, Map<String, Object> inParams, Connection dsConn){
		this.jsperInputStream = jsperInputStream;
		this.inParams = inParams;
		this.dsConn = dsConn;
	}
	
	public MyJasperRptWrapper(String jsperFullName, Map<String, Object> inParams, List<?> dsList){
		this.jsperFullName = jsperFullName;
		this.inParams = inParams;
		this.dsList = dsList;
	}
	public MyJasperRptWrapper(InputStream jsperInputStream, Map<String, Object> inParams, List<?> dsList){
		this.jsperInputStream = jsperInputStream;
		this.inParams = inParams;
		this.dsList = dsList;
	}
	
	public void setImg(String imgUrl, String imgDir){
		this.imgUrl = imgUrl;
		this.imgDir = imgDir;
	}

	
	private JasperPrint getJasperPrint(){
		try {
			if (jsperInputStream == null){
				if (jsperFullName != null){
					try {
						jsperInputStream = JRLoader.getFileInputStream(jsperFullName);
					} catch (JRException e) {
						
					}
				}else{
					throw new SSRptException("No template.");
				}
			}
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jsperInputStream);
			if (dsConn != null){
				return JasperFillManager.fillReport(jasperReport, inParams, dsConn);
			}else if (dsList != null){
				return JasperFillManager.fillReport(jasperReport, inParams, new JRBeanCollectionDataSource(dsList));
			}else{
				throw new SSRptException("Not set conn or list to fill data for jasper.");
			}
		} catch (JRException e) {
			throw new SSRptException(e.getMessage());
		}
	}
	
	public byte[] exportAsPDF() {
		JRPdfExporter expt = new JRPdfExporter();
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    expt.setParameter(JRExporterParameter.JASPER_PRINT, getJasperPrint());
	    expt.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	    try {
			expt.exportReport();
		} catch (JRException e) {
			throw new SSRptException(e);
		}
	    byte[] bytes = os.toByteArray();
	    
	    return bytes;
	}
	
	public byte[] exportAsXls() {
		JRXlsExporter expt = new JRXlsExporter();
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    expt.setParameter(JRExporterParameter.JASPER_PRINT, getJasperPrint());
	    expt.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	    try {
			expt.exportReport();
		} catch (JRException e) {
			throw new SSRptException(e);
		}
	    byte[] bytes = os.toByteArray();
	    
	    return bytes;
	}
	
	public byte[] exportAsHtml() {
		JRXhtmlExporter expt = new JRXhtmlExporter();
		
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    expt.setParameter(JRExporterParameter.JASPER_PRINT, getJasperPrint());
	    expt.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
	    if (this.imgUrl != null){
	    	expt.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imgDir);
	    	expt.setParameter(JRHtmlExporterParameter.IMAGES_URI, imgUrl);
	    	expt.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
	    	expt.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
	    }
	    try {
			expt.exportReport();
		} catch (JRException e) {
			throw new SSRptException(e);
		}
	    byte[] bytes = os.toByteArray();
	    
	    return bytes;
	}
	
}
