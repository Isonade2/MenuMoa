package com.wku.menumoa.controller;


import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.ResponseDTO;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.store.StoreDTO;
import com.wku.menumoa.dto.user.UserRegisterRequest;
import com.wku.menumoa.dto.user.UserRegisterResponse;
import com.wku.menumoa.service.EmailService;
import com.wku.menumoa.service.StoreService;
import com.wku.menumoa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final StoreService storeService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<UserRegisterResponse>> signup(@Valid @RequestBody UserRegisterRequest userRegisterRequest){

        Long save = userService.join(userRegisterRequest);



        System.out.println("saveId: " + save);
        return ResponseEntityBuilder.build("회원가입 성공", HttpStatus.OK, null);
    }

    @GetMapping("/activation")
    public String activation(@RequestParam(required = false) String code){
        log.info("[activation code] : {}", code);
        userService.activateUser(code);
        return "redirect:/";

    }

    @GetMapping("/checkEmail")
    public ResponseEntity<ResponseDTO<String>> checkEmail(@RequestParam(required = false) String email){
        log.info(email);
        /*
        이메일 확인하는 정규식 넣기
         */
        if (email == null || email.isEmpty()){
            return ResponseEntityBuilder.build("이메일을 입력해주세요.", HttpStatus.BAD_REQUEST, null);
        }
        if (!userService.checkEmail(email)){
            return ResponseEntityBuilder.build("사용 가능한 이메일입니다.", HttpStatus.OK, "Y");
        }
        else{
            return ResponseEntityBuilder.build("이미 사용중인 이메일입니다.", HttpStatus.OK, "N");
        }
    }

    @PostMapping("/loginfail")
    public ResponseEntity<?> fail(){
        return ResponseEntityBuilder.build("아이디 또는 비밀번호가 맞지 않습니다.", HttpStatus.UNAUTHORIZED, null);
    }

//    @PostMapping("/findPassword")
//    public ResponseEntity<?> resetPassword(@RequestParam String email){
//        if (userService.checkEmail(email)){
//            emailService.sendPasswordResetMail(email);
//            return ResponseEntityBuilder.build("비밀번호 재설정 메일을 보냈습니다.", HttpStatus.OK, null);
//        }
//        else{
//            return ResponseEntityBuilder.build("가입되지 않은 이메일입니다.", HttpStatus.OK, null);
//        }
//    }

    @GetMapping("/store")
    public ResponseEntity<?> getStores(@AuthenticationPrincipal User user){

        List<StoreDTO> storeDTOS = storeService.getStoresByUserId(user);

        return ResponseEntityBuilder.build("가게 조회 성공", HttpStatus.OK, storeDTOS);
    }


    @GetMapping("/test")
    public ResponseEntity<?> test(@AuthenticationPrincipal User user){
        return ResponseEntityBuilder.build("테스트 성공", HttpStatus.OK, user);
    }
}
