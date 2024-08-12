package com.wku.menumoa.controller;


import com.wku.menumoa.dto.ResponseDTO;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.user.UserRegisterRequest;
import com.wku.menumoa.dto.user.UserRegisterResponse;
import com.wku.menumoa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<UserRegisterResponse>> signup(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        Long save = userService.save(userRegisterRequest);


        return ResponseEntityBuilder.build("회원가입 성공", HttpStatus.OK, null);
    }
}
