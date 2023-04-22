package com.project.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller// 스프링이 com.project.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new 하는
//것이 아님. 특정 어노테이션이 붙어있는 클래스 파일들맘ㄴ new해서(Ioc) 스프링 컨테이너에 관리해줌.
public class BlogControllerTest {

    @GetMapping("/test/jsp")
    public String hello(){
        return "test";
    }
    @GetMapping("/test/home")
    public String home(){
        return "home.html";
    }

}
