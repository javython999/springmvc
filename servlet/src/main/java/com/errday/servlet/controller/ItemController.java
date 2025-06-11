package com.errday.servlet.controller;

import com.errday.servlet.annotationconverter.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping("/item")
    public String showForm(@ModelAttribute Item item) {
        return item.toString();
    }
}
