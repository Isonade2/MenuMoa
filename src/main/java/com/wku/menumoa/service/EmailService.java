package com.wku.menumoa.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public boolean sendMail(String to, String subject, String text){
        boolean msg = false;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("c1004sos1@gmail.com");
        simpleMailMessage.setText(text);

        try{
            mailSender.send(simpleMailMessage);
            msg = true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return msg;
    }

    public boolean sendSignUpMail(String to, String uuid){
        String subject = "NAMOO 서비스 회원가입 확인";
        String text = "NAMOO 서비스 회원가입 확인\n" +
                "아래의 링크를 클릭하여 계정을 활성화 후 로그인 해주세요.\n" +
                "링크의 유효 시간은 2시간 입니다.\n" +
                "계정 활성화하기: http://localhost:8080/member/verify?code=" + uuid;

        return sendMail(to, subject, text);
    }
}
