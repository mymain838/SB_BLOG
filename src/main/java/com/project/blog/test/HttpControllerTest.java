package com.project.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    //인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
    @GetMapping("/http/get")
    public String getTest(@RequestParam Member m){
        return "get 요청 " + m.getId();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestParam String text){
        return "post 요청" + text;
    }
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
