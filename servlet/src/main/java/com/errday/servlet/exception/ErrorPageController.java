package com.errday.servlet.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

    @GetMapping("/exception/exception")
    public String error() {
        throw new IllegalArgumentException("IllegalArgumentException error");
    }

    @GetMapping("/exception/401")
    public String error401() {
        return "error/401";
    }

    @GetMapping("/exception/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/exception/500")
    public String error500() {
        return "error/500";
    }


}
