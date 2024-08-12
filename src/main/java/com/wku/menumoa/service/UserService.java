package com.wku.menumoa.service;


import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.user.AddUserRequest;
import com.wku.menumoa.dto.user.UserRegisterRequest;
import com.wku.menumoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserRegisterRequest dto){
        User newUser = User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .build();

        userRepository.save(newUser);

        return newUser.getId();
    }
}
