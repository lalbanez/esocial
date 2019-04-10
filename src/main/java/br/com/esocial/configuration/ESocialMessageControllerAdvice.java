package br.com.esocial.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("br.com.esocial.controller")
public class ESocialMessageControllerAdvice {
	
	
//	/**
//	 * https://www.mkyong.com/spring-mvc/spring-mvc-exceptionhandler-example/
//	 */
//	@ExceptionHandler(NullPointerException.class)
//	public ModelAndView throw(NullPointerException ex) {
//		ModelAndView model = new ModelAndView("messages/error");
//		model.addObject("exception", ex);
//		return model;
//	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView throwExceptionView(Exception ex) {
		ex.printStackTrace();
		ModelAndView model = new ModelAndView("messages/erroEsocial");
		model.addObject("exception", ex);
		return model;
	}
	
	
	

}
