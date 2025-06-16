package com.errday.servlet.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "잘못된 비밀번호입니다. 비밀번호는 최소 8자 이상이어야 하고, 영문 대문자, 영문 소문자, 숫자를 포함해야 합니다.";
    int minLength() default 8;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
