package br.com.esocial.configuration;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import br.com.esocial.ESocialApplication;

public class ESocialServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ESocialApplication.class);
	}

//	 @Bean 
//	    public ClassLoaderTemplateResolver emailTemplateResolver(){ 
//	        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver(); 
//	        classLoaderTemplateResolver.setPrefix("templates/"); 
//	        classLoaderTemplateResolver.setTemplateMode("HTML5"); 
//	        classLoaderTemplateResolver.setCharacterEncoding("UTF-8"); 
//	        classLoaderTemplateResolver.setOrder(1); 
//
//	        return classLoaderTemplateResolver; 
//	    } 
//	 
//	 @Bean 
//	    public ServletContextTemplateResolver templateResolver() { 
//	        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(); 
//	        templateResolver.setTemplateMode("HTML5"); 
//	        templateResolver.setOrder(2); 
//	        return templateResolver; 
//	    }
}
