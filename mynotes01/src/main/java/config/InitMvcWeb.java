package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitMvcWeb extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ConfigCtxRoot.class, ConfigCtxSecurity.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ConfigCtxWeb.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
//put CharacterEncodingFilter before CsrfFilter in security setup. 	
//	@Override
//    protected Filter[] getServletFilters() {
//        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//        encodingFilter.setEncoding(CHAR_ENCODING);
//        encodingFilter.setForceEncoding(true);
//        return new Filter[] { encodingFilter };
//    }
	
	
	/*
    @Override
    protected void customizeRegistration(Dynamic registration) {
        //Set multipart
        MultipartConfigElement multipartConfig = new MultipartConfigElement("D:/myproject/stsgrd/sprInAct/smvcaa/tmp", 2097152, 4194304, 0);
        registration.setMultipartConfig(multipartConfig);
    }
	*/
}
