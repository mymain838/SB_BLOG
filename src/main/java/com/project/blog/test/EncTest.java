package com.project.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

    @Test
    public void 해쉬_암호화(){
        String rs = new BCryptPasswordEncoder().encode("1234");
        System.out.println(rs);
    }
}
