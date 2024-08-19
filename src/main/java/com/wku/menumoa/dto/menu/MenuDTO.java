package com.wku.menumoa.dto.menu;


import com.wku.menumoa.domain.Menu;
import com.wku.menumoa.domain.Store;
import com.wku.menumoa.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    /*
    json 요청 형식
ㄱ
 */
    private String name;
    private String price;
    private String content;
    private String pictureURL;
    private String category;

    public Menu toEntity(Store store){
        return Menu.builder()
                .store(store)
                .name(name)
                .price(price)
                .content(content)
                .pictureURL(pictureURL)
                .category(category)
                .build();
    }
}



