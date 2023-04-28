package com.project.blog.service;

import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Transactional(readOnly = true) //Select 할 때 트렌잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//    public User 로그인(User user) {
//
//       return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//
//    }
}
