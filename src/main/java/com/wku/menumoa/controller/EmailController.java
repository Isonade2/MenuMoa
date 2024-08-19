package com.wku.menumoa.controller;


import com.wku.menumoa.service.EmailService;
import com.wku.menumoa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;
    private final UserService userService;
    
    @PostMapping("/sendMail")
    public void sendMail(){
        log.info("sendMail");
    }



}
