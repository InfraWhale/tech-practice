package com.cos.security1.service;

import com.cos.security1.dto.CustomUserDetails;
import com.cos.security1.entity.User;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// "/login" 경로로 POST 요청이 올 경우 :
// UsernamPasswordAuthenticationFilter가 동작한다
// AuthenticationProvider에 의해, CustomUserDetailsService의 loadUserByUsername을 호출한다.

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자가 로그인을 하면 스프링 시큐리티가 여기에 username을 넣어준다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userData = userRepository.findByUsername(username);

        if(userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
