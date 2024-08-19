package com.wku.menumoa;

import com.wku.menumoa.domain.Menu;
import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import com.wku.menumoa.repository.MenuRepository;
import com.wku.menumoa.repository.StoreRepository;
import com.wku.menumoa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class domainTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepositoty;



    @Test
    public void test(){
        System.out.println("domainTest");
    }

    @Test
    public void DBTest(){
        User user = User.builder()
                .id(1L)
                .email("c1004sos@naver.com")
                .password("1234")
                .category("로컬")
                .nickname("몸무")
                .build();
        userRepository.save(user);

        Store store = Store.builder()
                .id(1L)
                .name("하나요리당고")
                .tel("010-1234-5678")
                .content("하나요리당고입니다.")
                .category("일식")
                .user(user)
                .build();
        storeRepository.save(store);

        Menu menu = Menu.builder()
                .id(1L)
                .name("돈코츠라멘")
                .price("10000")
                .content("돈코츠라멘입니다.")
                .category("음식")
                .store(store)
                .build();
        menuRepositoty.save(menu);
    }
}
