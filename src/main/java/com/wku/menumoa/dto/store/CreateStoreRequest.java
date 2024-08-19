package com.wku.menumoa.dto.store;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoreRequest {
    private String name;
    private String address;
    private String tel;
    private String content;
    private String category;


}
