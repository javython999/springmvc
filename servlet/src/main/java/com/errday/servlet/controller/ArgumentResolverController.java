package com.errday.servlet.controller;

import com.errday.servlet.annotation.CurrentUser;
import com.errday.servlet.domain.LoginUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class ArgumentResolverController {

    @GetMapping("/users")
    public String users(@CurrentUser LoginUser loginUser, Model model) {
        if (loginUser == null) {
            return "noUser";
        }

        return loginUser.toString();
    }
}
