package com.project.blog.service;

import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    // DI 주입 : 생성자
/*
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encode){
        this.userRepository = userRepository;
        this.encode = encode;
    }
 */

    // DI 주입 : 필드
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {
        user.setRole(RoleType.USER);
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);

    }
    @Transactional
    public String 회원수정(User reqUser) {
        User user = userRepository.findById(reqUser.getId()).orElseThrow((() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        }));
        String rawPassword = reqUser.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setEmail(reqUser.getEmail());


        return "OK";
        //회원 수정 함수 종료시 = 서비스 종료 - 트랜잭션 종료 - commit 자동으로 됨.
        //영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }
    @Transactional(readOnly = true)
    public User 회원찾기(String username) {

        User user = userRepository.findByUsername(username).orElseGet(() -> {
           return new User();
        });

        return user;
    }


//    @Transactional(readOnly = true) //Select 할 때 트렌잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//    public User 로그인(User user) {
//
//       return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//
//    }
}
