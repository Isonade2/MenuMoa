package com.wku.menumoa.controller;


import com.wku.menumoa.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;
    
    @PostMapping("/sendMail")
    public void sendMail(){
        log.info("sendMail");
        emailService.sendMail("c1004sos1@gmail.com", "test", "테스트입니다.");
    }
}
