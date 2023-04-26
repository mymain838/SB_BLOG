package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/joinForm")
    public String join(){

        return "user/joinForm";


    }
    @GetMapping("/loginForm")
    public String login(){

        return "user/loginForm";


    }

}
