package com.wku.menumoa.dto.store;


import com.wku.menumoa.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {
    private Long id;
    private String name;
    private String tel;
    private String category;
    private String address;
    private String pictureURL;
    private boolean isPosted;


    public StoreDTO(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.tel = store.getTel();
        this.category = store.getCategory();
        this.address = store.getAddress();
        this.pictureURL = store.getPictureURL();
        this.isPosted = store.isPosted();
    }
}
