package com.cos.security2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {

        //// 일회용 세션이 있어서 여기서 필요 정보를 가져올 수 있다.

        // 세션에서 사용자 아이디 가져오고 싶은 경우
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        // 세션에서 사용자 역할 가져오고 싶은 경우
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        
        return "main Controller " + name + " " + role;
    }
}
