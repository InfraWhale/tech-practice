package com.cos.security2.controller;

import com.cos.security2.dto.JoinDto;
import com.cos.security2.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDto joinDto) {

        joinService.joinProcess(joinDto);

        return "ok";
    }
}
