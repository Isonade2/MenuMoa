package com.wku.menumoa.controller;

import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.menu.CreateMenuDTO;
import com.wku.menumoa.dto.menu.MenuDTO;
import com.wku.menumoa.repository.MenuRepository;
import com.wku.menumoa.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Slf4j
public class MenuController {
    private final MenuRepository menuRepository;
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<?> createMenu (@RequestBody CreateMenuDTO dto, @AuthenticationPrincipal User user){
        log.info("[AddMenuDto] = " + dto.toString());
        log.info("[User] = " + user.toString());
        menuService.createMenu(dto,user.getId());

        return ResponseEntityBuilder.build("메뉴 추가 성공", HttpStatus.OK,dto);

    }
}
