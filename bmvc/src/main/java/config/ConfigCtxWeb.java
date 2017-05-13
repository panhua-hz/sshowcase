package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("controller")
public class ConfigCtxWeb extends WebMvcConfigurerAdapter {
	@Bean
    public ViewResolver viewResolver() {
        // 若不配置视图解析器，默认会使用BeanNameViewResolver,它会查询id与视图名称匹配的并实现了view接口的bean.
        // 配置JSP视图解析器
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        //JSTL会获取Locale对象，以便格式化地域相关的值，如日期，货币等
        //设置不同的viewclass可以处理不同的资源
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    @Bean
    public MessageSource messageSource() {
        //加载resourceBundle, 可以被<s:message>所用。i18n
        //ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //messageSource.setDefaultEncoding("UTF-8");
        //messageSource.setBasename("jstl.message.messages");
        //这个bean将被JstlView注入 see: JstlView.
        
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/i18n/messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }
    @Override
    public Validator getValidator() {
        //用来设置validator，设置路径。
        //return super.getValidator();
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource());
        return factory;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置静态资源处理:要求DispatcherServlet将对静态资源的请求转发
        // 如: http://localhost:8080/esprmvc0/assets/css/custom_style.css
        // http://localhost:8080/esprmvc0/assets/image/2.jpg
        // 注意，一般来说静态资源放在webapp下，而不要放在WEB-INF下. WEB-INF的资源默认的servlet handler处理不了，因为它是受保护的。
        // enable后我觉得是如果别的viewresolver处理不了就交给它处理。
        //还有一种方式是addResourceHandlers.
        configurer.enable();
    }
    
    /*
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        //把资源重定位
        registry.addResourceHandler("/html/**").addResourceLocations("/WEB-INF/html/");
    }
    */

    /*
    @Bean
    public MultipartResolver multipartResolver() throws IOException{
        return new StandardServletMultipartResolver();
    }
    */
}
