package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링 부트의 설정 클래스로 등록한다.
@EnableWebSecurity // 스프링 시큐리티에 의해 관리하도록 한다.
public class SecurityConfig {

    // 단방향 암호화를 쓸 것임
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // 계층 주기
    @Bean
    public RoleHierarchy roleHierarchy() {

        return RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_MANAGER
                ROLE_MANAGER > ROLE_USER
        """);
    }
//    @Bean
//    public RoleHierarchy roleHierarchy() {
//
//        return RoleHierarchyImpl.withDefaultRolePrefix()
//                .role("C").implies("B")
//                .role("B").implies("A")
//                .build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // 이 안에 인증/인가 로직을 작성한다.

        // 각 페이지 별로 제한을 줄 수 있다.
        // 맨 위에 모든 페이지를 허용해버리면, 이후에 requestMatchers를 썼을 때 적용이 안 될 수도 있다. -> 순서 잘 생각할 것!
//        http.authorizeHttpRequests((auth) -> auth
//                .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/manager").hasAnyRole("ADMIN", "MANAGER")
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "MANAGER", "USER") // 와일드카드 : **
//                        .anyRequest().authenticated()
//                );
        
        // 계층화 적용
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/manager").hasAnyRole("MANAGER")
                        .requestMatchers("/my/**").hasAnyRole("USER") // 와일드카드 : **
                        .anyRequest().authenticated()
                );


        // formLogin -> auth.loginPage("/login") : 권한이 없는 등의 이슈가 있을 경우 가야 할 로그인 페이지 경로를 잡아준다.
        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc") // 로그인을 담당하는 UsernamePasswordAuthenticationFilter가 어떤 주소에 반응할지 set 해준다.
                .permitAll() // "/login", "/loginProc"은 모든 사용자가 가능하게 한다.
        );

        // 위에 꺼 말고 httpBasic 사용
        // 주로 내부망의 서버간 통신을 진행하는 경우 사용
//        http
//                .httpBasic(Customizer.withDefaults());




        // csrf disable. 사이트 위변조 방지 설정. post 요청 보낼 시, 토큰을 보내야만 로그인이 진행된다.
        // 현재 이게 구현이 안되어있으니 잠시 꺼둔다.


        // 다중 로그인 구현
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 하나의 아이디에서 허용할 수 있는 동시접속 중복 로그인.
                        .maxSessionsPreventsLogin(true)); // 위 갯수를 초과하였을 경우. true : 초과시 로그인 차단. false : 기존 세션 하나 삭제

        // csrf 보호
        // (Cross-Site Request Forgery)
        // : 요청을 위조하여, 사용자가 원하지 않아도 특정 요청을 강제로 보내게 하는 공격방식이다.
        //        http
        //                .csrf((auth) -> auth.disable());
        // 위 코드가 없으면, post put delete시 토큰 검증을 진행한다.
        // JWT는 stateless여서 해당 공격이 먹히지 않는다.

        // 세션 고정보호
        // 세션 고정공격 : 공격자가 사용자의 세션 ID를 미리 확보한 뒤, 사용자가 해당 ID로 로그인하도록 유도해 세션을 가로채는 공격
        // 이를 방지하기 위해 로그인 시 세션 ID를 변경해 사용자가 안전한 세션을 사용하도록 함

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation()
                        .changeSessionId()); // 로그인 시 동일한 세션에 대한 id 변경

        return http.build();
    }
}
