package com.example.aplicacionwebfilmotokio.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleException(HttpServletRequest request, RuntimeException re) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", re.getMessage());
        modelAndView.addObject("url", request.getRequestURL().toString());
        modelAndView.setViewName("error/error");
//        re.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(HttpServletRequest request, MaxUploadSizeExceededException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "El tamaño maximo para subir una imagen es de 2MB");
        modelAndView.addObject("url", request.getRequestURL().toString());
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}