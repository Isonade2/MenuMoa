package com.wku.menumoa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.ResponseEntityBuilder;
import com.wku.menumoa.dto.store.CreateStoreRequest;
import com.wku.menumoa.dto.store.StoreDTO;
import com.wku.menumoa.repository.StoreRepository;
import com.wku.menumoa.service.S3ImageService;
import com.wku.menumoa.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreService storeService;
    private final S3ImageService s3ImageService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getStores(){
        List<StoreDTO> stores = storeService.getStores();

        return ResponseEntityBuilder.build("가게 조회 성공", HttpStatus.OK, stores);
    }


    @PostMapping
    public ResponseEntity<?> createStore(@RequestPart("image") MultipartFile image, @RequestPart("store") String storeJson, @AuthenticationPrincipal User user) throws Exception{
        log.info("[UserId] = " + user.getId());

        ObjectMapper objectMapper = new ObjectMapper();
        CreateStoreRequest dto = objectMapper.readValue(storeJson, CreateStoreRequest.class);

        log.info("[store] = " + dto.toString());

        if(image != null){
            String img = s3ImageService.upload(image);
            dto.setPictureURL(img);
        }

        storeService.createStore(dto, user.getId());

        return ResponseEntityBuilder.build("가게 추가 성공", HttpStatus.OK,dto);
    }

    @GetMapping("/delete/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable Long storeId, @AuthenticationPrincipal User user){
        log.info("[DeleteStoreRequest] = " + storeId);
        log.info("[User.id] = " + user.getId());
        storeService.deleteStore(storeId, user.getId());
        return ResponseEntityBuilder.build("가게 삭제 성공", HttpStatus.OK, storeId);
    }

    //폼데이터 형식으로 이미지파일과 json형식의 가게정보를 받아서 가게를 추가하는 api
    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestPart("file") MultipartFile image, @RequestPart("store") String storeJson) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CreateStoreRequest createStoreRequest = objectMapper.readValue(storeJson, CreateStoreRequest.class);
        log.info("[store] = " + createStoreRequest.toString());

        String img = s3ImageService.upload(image);

        log.info(img);
        return ResponseEntityBuilder.build("가게 추가 성공", HttpStatus.OK, null);
    }
}
