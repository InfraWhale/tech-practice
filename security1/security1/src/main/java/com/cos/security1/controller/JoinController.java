package com.cos.security1.controller;

import com.cos.security1.dto.JoinDto;
import com.cos.security1.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;
    @GetMapping("/join")
    public String joinP() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto) {

        log.info(joinDto.getUsername());

        joinService.joinProcess(joinDto);

        return "redirect:/login";
    }
}
