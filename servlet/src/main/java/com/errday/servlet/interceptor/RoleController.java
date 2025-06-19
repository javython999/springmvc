package com.errday.servlet.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class RoleController {

    @GetMapping("/profile")
    public String profile(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "userProfile";
    }

    @GetMapping("/admin")
    @ResponseBody
    @AdminOnly
    public String admin() {
        return "welcome Admin";
    }



}
