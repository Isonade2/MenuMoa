package com.wku.menumoa.service;


import com.wku.menumoa.domain.EmailVerify;
import com.wku.menumoa.repository.EmailVerifyRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailVerifyRepository emailVerifyRepository;



    public MimeMessage createMessage(String email, String uuid){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        String subject = "메뉴모아 서비스 회원가입 확인";
    //    String text = "메뉴모아 서비스 회원가입 확인\n" +
    //            "아래의 링크를 클릭하여 계정을 활성화 후 로그인 해주세요.\n" +
    //            "링크의 유효 시간은 2시간 입니다.\n" +
    //            "계정 활성화하기: http://localhost:8080/member/verify?code=" + uuid;

        String text = "<html><body>" +
                "<h1>메뉴모아 서비스 회원가입 확인</h1>" +
                "<p>아래의 링크를 클릭하여 계정을 활성화 후 로그인 해주세요.</p>" +
                "<p>링크의 유효 시간은 2시간 입니다.</p>" +
                "<a href='http://localhost:8080/user/activation?code=" + uuid + "'>계정 활성화하기</a>" +
                "</body></html>";
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        } catch (MessagingException e){
            log.error(e.getMessage());
        }
        return mimeMessage;
    }


    public void sendSignUpMail(EmailVerify emailVerify){
        MimeMessage message = createMessage(emailVerify.getEmail(), emailVerify.getUuid());

        log.info("[Mail 전송 시작");
        try{
            mailSender.send(message);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("[Mail 전송 완료]");

        emailVerifyRepository.save(emailVerify);

    }
}
