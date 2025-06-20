package com.errday.servlet.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ControllerAdviceController {

    @GetMapping("/model")
    public String model(Model model) {
        model.addAttribute("message", "Welcome to Spring MVC");
        return "home";
    }

    @GetMapping("/date")
    public String date(@RequestParam Date date, Model model) {
        model.addAttribute("date", date);
        return "home";
    }

    @GetMapping("/exception")
    public String exception() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}
