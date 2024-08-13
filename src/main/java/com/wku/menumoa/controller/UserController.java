package com.wku.menumoa.controller;


import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.ResponseDTO;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.user.UserRegisterRequest;
import com.wku.menumoa.dto.user.UserRegisterResponse;
import com.wku.menumoa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<UserRegisterResponse>> signup(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        Long save = userService.save(userRegisterRequest);


        return ResponseEntityBuilder.build("회원가입 성공", HttpStatus.OK, null);
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("check");

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")){
            User userDetails = (User) authentication.getPrincipal();
            var userData = new HashMap<>();
            userData.put("id", userDetails.getId());
            userData.put("email", userDetails.getEmail());
            userData.put("name", userDetails.getNickname());
            return ResponseEntityBuilder.build("로그인 완료", HttpStatus.OK, userData);
        }
        else{
            return ResponseEntityBuilder.build("로그인되어있지 않습니다.", HttpStatus.NOT_ACCEPTABLE, null);
        }
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> currentUserName(@AuthenticationPrincipal User principal){
        String username = principal.getUsername();
        return ResponseEntityBuilder.build("로그인 완료", HttpStatus.OK, username);
    }

    @PostMapping("/loginfail")
    public ResponseEntity<?> fail(){
        return ResponseEntityBuilder.build("아이디 또는 비밀번호가 맞지 않습니다.", HttpStatus.UNAUTHORIZED, null);
    }
}
