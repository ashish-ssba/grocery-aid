package com.github.prdobby.grocery.aid.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionMappingHandler {

    @Value("${spring.application.name}")
    String appName;

    private static final Logger log = LoggerFactory.getLogger(ExceptionMappingHandler.class);

    @ExceptionHandler(NumberFormatException.class, IllegalArgumentException.class)
    public ModelAndView badRequest(final Exception e) {
        log.warn("Received bad user input", e);

        final ModelAndView mav = initializeModelAndView();
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.setViewName("error/4xx");
        mav.addObject("message", e.getMessage());

        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView serverError(final Exception e) {
        log.error("An error occurred when processing a user request", e);

        final ModelAndView mav = initializeModelAndView();
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mav.setViewName("error/5xx");
        
        return mav;
    }

    private ModelAndView initializeModelAndView() {
        final ModelAndView mav = new ModelAndView();
        mav.addObject("appName", appName);

        return mav;
    }
}
