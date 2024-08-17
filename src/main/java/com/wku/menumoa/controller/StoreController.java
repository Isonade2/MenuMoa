package com.wku.menumoa.controller;

import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.store.AddStoreRequest;
import com.wku.menumoa.repository.StoreRepository;
import com.wku.menumoa.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody AddStoreRequest dto, @AuthenticationPrincipal User user){
        log.info("[AddStoreRequest] = " + dto.toString());
        log.info("[User] = " + user.toString());
        log.info("[UserId] = " + user.getId());

        storeService.createStore(dto, user.getId());



        return ResponseEntityBuilder.build("가게 추가 성공", HttpStatus.OK,dto);
    }
}
