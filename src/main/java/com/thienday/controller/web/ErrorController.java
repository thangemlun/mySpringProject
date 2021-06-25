package com.thienday.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {	
	
	@ExceptionHandler(ResourceNotFoundException.class)
    private ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex , HttpServletRequest request ) {
		System.out.println(request.getContextPath());
        return new ModelAndView("redirect:/PageNotFound");
    }
	

}
