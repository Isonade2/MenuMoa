package com.wku.menumoa.dto.store;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoreRequest {
    @NotBlank(message = "가게 이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "가게 주소를 입력해주세요.")
    private String address;
    @NotBlank(message = "가게 전화번호를 입력해주세요.")
    private String tel;
    @NotBlank(message = "가게 소개를 입력해주세요.")
    private String content;
    @NotBlank(message = "가게 카테고리를 입력해주세요.")
    private String category;


}
