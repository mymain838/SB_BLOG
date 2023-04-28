package com.project.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 빈 등록 : 스프링 컨테
@Configuration// 빈등록(Ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다. = 스프링 시큐리티가 활성화가 되어 있는데
// 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소를 접근을 하면 권한 및 인증을 미리 체크하곗다는 뜻.
/*
 * Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter 는 Deprecated.
 * -> SecurityFilterChain, WebSecurityCustomizer 를 상황에 따라 빈으로 등록해 사용한다.
 */
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws  Exception{

        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests() // 요청이 들어오면
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //auth 밑에 있는 요청이면
                .permitAll() // 따로 권한을 줘
                .anyRequest() // 따로 권한을 주지않는 요청들은
                .authenticated() // 인증을 해야해
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");



        return http.build();
    }
}
