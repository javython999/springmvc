package com.errday.servlet.validation;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Admin {

    @NotEmpty(message = "비밀번호는 필수입니다.")
    @ValidPassword(minLength = 10, message = "잘못된 비밀번호입니다. 비밀번호는 최소 10자 이상이어야하고, 영문 대문자, 영문 소문자, 숫자를 포함해야 합니다.")
    private String password;
}
