package com.errday.servlet.controller;

import com.errday.servlet.domain.User;
import com.errday.servlet.typeconvert.domain.Url;
import com.errday.servlet.typeconvert.factory.OrderColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController
public class SpringConverterController {

    @PostMapping("/url")
    public String saveUrl(@ModelAttribute("url") Url url) {
        return "url: " + url;
    }

    @PostMapping("/users")
    public User user(@RequestParam("user") User user) {
        return user;
    }

    @PostMapping("/color")
    public OrderColor color(@RequestParam("orderColor") OrderColor orderColor) {
        return orderColor;
    }
}
