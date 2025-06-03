package com.errday.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModelAttributeController {

    @GetMapping("/users")
    public String getUserForm(Model model) {
        //model.addAttribute("user", new User());
        return "userForm";
    }

    /*
    @PostMapping("/users")
    public String saveUser(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        User user = new User(username, email);
        model.addAttribute("user", user);

        return "userForm";
    }
    */

    @PostMapping("/users/{user}")
    public String saveUser(@ModelAttribute User user) {
        return "userForm";
    }

    @ModelAttribute
    public User addUser() {
        return new User("default", "default@default.com");
    }


}
