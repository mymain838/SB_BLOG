package com.project.blog.handler;

import com.project.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 익셉션 처리시 클래스 진입
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class) // IAE 걸리면
    public ResponseDto<String> handleArgumentException(Exception e){

        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
