package tools;

import static tools.IOUtil.rwriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.SSParseRequestException;
import exceptions.SSWriteResponseException;


public class WebHandler {	
	private static final Logger logger = LoggerFactory.getLogger(WebHandler.class);	
	public static String parseRequestXml(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream is = request.getInputStream();
			Reader wrd = new InputStreamReader(is, "UTF-8");
			StringWriter sw = new StringWriter();
			rwriter(wrd, sw);
			logger.info(sw.toString());
			return sw.toString();
		} catch (Exception e) {
			throw new SSParseRequestException("Parse request error.", e);
		}
	}

	public static void writeResponseXml(HttpServletResponse response, String outputXml) {
		try {
			response.setContentType("application/xml; charset=UTF-8;pageEncoding=UTF-8");
			response.getWriter().append(outputXml);
		} catch (IOException e) {
			throw new SSWriteResponseException("Write response xml error.", e);
		}
	}

	public static void writeResponseData(HttpServletResponse response, byte[] content) {
		try {
			OutputStream rspos = response.getOutputStream();
			IOUtil.write(content, rspos);
		} catch (IOException e) {
			throw new SSWriteResponseException("Write response data error.", e);
		}
	}
	
	public static void writeResponseHtml(HttpServletResponse response, byte[] content) {
		try {
			response.setContentType("text/html");
			OutputStream rspos = response.getOutputStream();
			IOUtil.write(content, rspos);
		} catch (IOException e) {
			throw new SSWriteResponseException("Write response data error.", e);
		}
	}
	public static void writeResponsePdf(HttpServletResponse response, byte[] content) {
		try {
			response.setContentType("application/pdf");
			OutputStream rspos = response.getOutputStream();
			IOUtil.write(content, rspos);
		} catch (IOException e) {
			throw new SSWriteResponseException("Write response data error.", e);
		}
	}
	

	public static void writeResponseExlData(HttpServletResponse response, byte[] content) {
		try {
			response.setContentType("application/vnd.ms-excel");
			// response.setHeader("contentDisposition",
			// "filename=\"RFA_Data.xls\"");
			OutputStream rspos = response.getOutputStream();
			IOUtil.write(content, rspos);
		} catch (IOException e) {
			throw new SSWriteResponseException("Write response data error.", e);
		}
	}
}
