package com.wku.menumoa.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {
    @NotBlank
    private String email;
    private String password;
    private String nickname;
}
