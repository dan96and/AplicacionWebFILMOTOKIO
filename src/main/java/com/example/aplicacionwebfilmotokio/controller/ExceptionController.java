package com.example.aplicacionwebfilmotokio.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, RuntimeException re) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", re.getMessage());
        modelAndView.addObject("url", request.getRequestURL().toString());
        modelAndView.setViewName("error/error");

        return modelAndView;
    }

}
