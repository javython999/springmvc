package com.errday.servlet.controller;

import com.errday.servlet.annotationconverter.MyModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("myModel", new MyModel());
        return "formatForm";
    }

    @PostMapping("/submit")
    public String handleSubmit(@ModelAttribute("myModel") MyModel myModel, Model model) {
        model.addAttribute("myModel", myModel);
        return "formatResult";
    }
}
