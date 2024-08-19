package com.wku.menumoa.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank(message = "이메일을 입력해주세요.") @Email(message = "올바른 형식의 주소여야 합니다. 이메일 형식을 확인해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$", message = "비밀번호는 영문, 숫자를 포함한 8~20자여야 합니다.")
    private String password;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
}
