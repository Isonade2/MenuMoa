package com.wku.menumoa.service;


import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.store.AddStoreRequest;
import com.wku.menumoa.repository.StoreRepository;
import com.wku.menumoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    public void createStore(AddStoreRequest dto, Long userId){
        log.info("[StoreService][AddStoreRequest] = " + dto.toString());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Store store = Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .tel(dto.getTel())
                .category(dto.getCategory())
                .content(dto.getContent())
                .user(user)
                .build();

        storeRepository.save(store);
    }


}
