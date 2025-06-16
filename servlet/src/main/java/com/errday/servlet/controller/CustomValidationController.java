package com.errday.servlet.controller;

import com.errday.servlet.validation.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomValidationController {

    @PostMapping("/admin")
    public String admin(@Validated Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            return "error: " + result.getAllErrors();
        }

        return "success: " + admin;
    }
}
