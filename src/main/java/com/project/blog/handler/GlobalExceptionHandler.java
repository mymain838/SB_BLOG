package com.project.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 익셉션 처리시 클래스 진입
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class) // IAE 걸리면
    public String handleArgumentException(Exception e){

        return "<h1>"+e.getMessage()+"</h1>";
    }
}
