package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.config.auth.PrincipalDetailService;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("user Api Controoler save 호출됌");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PutMapping("/user")
    public ResponseDto<String> update(@RequestBody User user){
      String rs = userService.회원수정(user);

      Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
      SecurityContext securityContext = SecurityContextHolder.getContext();
      securityContext.setAuthentication(authentication);
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        return new ResponseDto<String>(HttpStatus.OK.value(), rs);
    }
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//        System.out.println("user Api Controoler login 호출됌");
//        User principal = userService.로그인(user); //principal(접근주체)
//        if(principal !=null){
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
}
