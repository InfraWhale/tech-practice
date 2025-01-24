package com.cos.security1.service;

import com.cos.security1.dto.JoinDto;
import com.cos.security1.entity.User;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {

        // db에 이미 동일한 정보 가진 회원 존재하는지 검증한다.
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if(isUser) {
            return;
        }

        User user = new User();

        user.setUsername(joinDto.getUsername());
        //user.setPassword(joinDto.getPassword()); // 암호화 진행하여야 한다!
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));

        user.setRole("ROLE_ADMIN"); // ROLE_USER

        userRepository.save(user);
    }
}
