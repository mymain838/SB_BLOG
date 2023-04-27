package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//인증이 안된 사용자들이 출입 할 수 있는 경로를 /auto/..* 허용
// 그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**
@Controller
public class UserController {
    @GetMapping("auth/joinForm")
    public String join(){

        return "user/joinForm";


    }
    @GetMapping("auth/loginForm")
    public String login(){

        return "user/loginForm";


    }

}
