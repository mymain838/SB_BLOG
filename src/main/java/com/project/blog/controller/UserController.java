package com.project.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.model.KakaoProfile;
import com.project.blog.model.OAuthToken;
import com.project.blog.model.User;
import com.project.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입 할 수 있는 경로를 /auto/..* 허용
// 그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**
@Controller
public class UserController {
    @Value("${cos.key}")
    private String cosKey;


    @Autowired
    private UserService userService;

    @GetMapping("auth/joinForm")
    public String join() {

        return "user/joinForm";


    }

    @GetMapping("auth/loginForm")
    public String login() {

        return "user/loginForm";


    }

    @GetMapping("/user/updateForm")
    public String updateForm() {




        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {
        System.out.println(cosKey);
        //POST 방식으로 key=value 데이터 요청(카카오쪽으로)
        //Retrofit2
        //OkHttp
        //RestTemplate
        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "7b8954aeef8a543f6db2c9aadfa30d18");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Htt 요청하기 - Post방식으로 그리고 response 변수의 응답을 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson,Json Simple, ObjectMapper
        OAuthToken oAuthToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(oAuthToken.getAccess_token());


        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성

        //HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        // Htt 요청하기 - Post방식으로 그리고 response 변수의 응답을 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        //Gson,Json Simple, ObjectMapper
        KakaoProfile kakaoProfile = null;
        ObjectMapper objectMapper2 = new ObjectMapper();
        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //User 오브젝트 : username,password,email
        System.out.println(kakaoProfile.getId());
        System.out.println(kakaoProfile.getKakao_account().getEmail());

        System.out.println("블로그 서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("블로그 서버 이메일: " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("블로그서버 패스워드: "+ cosKey);

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                        .password(cosKey)
                                .email(kakaoProfile.getKakao_account().getEmail())
                                     .oauth("kakao")
                                            .build();
        // 가입자 혹은 비가입자 체크 해서 처리
        User originUser = userService.회원찾기(kakaoUser.getUsername());
        if(originUser.getUsername() == null){
            System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
            userService.회원가입(kakaoUser);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        return "redirect:/";

    }

}
