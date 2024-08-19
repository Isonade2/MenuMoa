package com.wku.menumoa.service;


import com.wku.menumoa.domain.EmailVerify;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.user.UserRegisterRequest;
import com.wku.menumoa.repository.EmailVerifyRepository;
import com.wku.menumoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final EmailVerifyRepository emailVerifyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long join(UserRegisterRequest dto){

        if (checkEmail(dto.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }



        User newUser = User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .role("ROLE_USER")
                .category("LOCAL")
                .status("INACTIVE")
                .build();
        userRepository.save(newUser);

        EmailVerify emailVerify = EmailVerify.builder()
                .email(dto.getEmail())
                .uuid(UUID.randomUUID().toString())
                .user(newUser)
                .build();

        emailService.sendSignUpMail(emailVerify);


        return newUser.getId();
    }

    public void activateUser(String uuid){
        EmailVerify emailVerify = emailVerifyRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 요청입니다."));

        User user = emailVerify.getUser();
        user.changeStatus("ACTIVE");

        emailVerifyRepository.delete(emailVerify);
    }


    public boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
