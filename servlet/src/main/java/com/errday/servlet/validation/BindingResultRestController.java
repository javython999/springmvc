package com.errday.servlet.validation;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BindingResultRestController {

    @PostMapping("/validation")
    public User validation(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        return user;
    }

    @PostMapping("/non-validation")
    public User validation(@ModelAttribute User user) {
        return user;
    }

    @PostMapping("/validation/2")
    public User validation(@ModelAttribute User user, BindingResult bindingResult) {
        return user;
    }

    @PostMapping("/users")
    public User registerUser(@ModelAttribute User user, BindingResult bindingResult) {
        if (!StringUtils.hasText(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "username", "사용자 이름은 필수입니다."));
        }

        if (!StringUtils.hasText(user.getUsername()) && user.getAge() < 1) {
            bindingResult.addError(new ObjectError("user", "사용자 정보가 잘못 되었습니다."));
        }

        return user;
    }
}
