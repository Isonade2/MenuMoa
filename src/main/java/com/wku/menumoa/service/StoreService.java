package com.wku.menumoa.service;


import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.dto.store.CreateStoreRequest;
import com.wku.menumoa.dto.store.StoreDTO;
import com.wku.menumoa.repository.StoreRepository;
import com.wku.menumoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    public void createStore(CreateStoreRequest dto, Long userId){
        log.info("[StoreService][AddStoreRequest] = " + dto.toString());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Store store = Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .tel(dto.getTel())
                .category(dto.getCategory())
                .content(dto.getContent())
                .pictureURL(dto.getPictureURL())
                .user(user)
                .build();

        storeRepository.save(store);
    }

    public List<StoreDTO> getStoresByUserId(User user){
        List<Store> stores = storeRepository.findByUserId(user.getId());
        List<StoreDTO> storeDTOS = stores.stream().map(StoreDTO::new).toList();
        return storeDTOS;
    }

    public List<StoreDTO> getStores(){
        List<Store> stores = storeRepository.findByIsPostedTrue();
        List<StoreDTO> storeDTOS = stores.stream().map(StoreDTO::new).toList();
        return storeDTOS;
    }

    public void deleteStore(Long storeId, Long userId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        if (!store.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("가게를 삭제할 권한이 없습니다.");
        }

        storeRepository.delete(store);

    }

}
