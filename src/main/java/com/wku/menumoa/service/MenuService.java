package com.wku.menumoa.service;

import com.wku.menumoa.domain.Store;
import com.wku.menumoa.dto.menu.CreateMenuDTO;
import com.wku.menumoa.repository.MenuRepository;
import com.wku.menumoa.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;


    /*
    메뉴 추가 시 json 요청 형식
    {
  "menus": [
    {
      "name": "햄버거",
      "price": 5000,
      "content": "햄버거입니다.",
      "pictureURL": "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg",
      "category": "양식"
    },
    {
      "name": "피자",
      "price": 15000,
      "content": "치즈 피자입니다.",
      "pictureURL": "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg",
      "category": "양식"
    },
    {
      "name": "김밥",
      "price": 2000,
      "content": "참치 김밥입니다.",
      "pictureURL": "https://cdn.pixabay.com/photo/2017/08/07/18/57/delicious-2609229_960_720.jpg",
      "category": "한식"
    }
  ]
}
     */


    public void createMenu(CreateMenuDTO menus, Long userId){
        log.info("[MenuService][AddMenuDto] = " + menus.toString());

        Store store = storeRepository.findById(menus.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

// 메뉴 추가
        menus.getMenus().forEach(menu -> {
            menuRepository.save(menu.toEntity(store));
        });
    }
}
