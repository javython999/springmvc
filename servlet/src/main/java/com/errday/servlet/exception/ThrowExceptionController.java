package com.errday.servlet.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ThrowExceptionController {

    @GetMapping("/exception")
    public String error() {
        throw new IllegalArgumentException("IllegalArgumentException error");
    }

    @GetMapping("/error500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }

    @GetMapping("/error404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404);
    }

    @GetMapping("/error401")
    public void error401(HttpServletResponse response) throws IOException {
        response.sendError(401);
    }
}
