package com.errday.servlet.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InterceptorController {

    @GetMapping("/api/users")
    public String users() {
        return "hello CustomInterceptor1";
    }

    @GetMapping("/api/public/users")
    public String publicUsers() {
        return "hello CustomInterceptor1";
    }

    @GetMapping("/login")
    public String login() {
        return "hello CustomInterceptor2";
    }

    @GetMapping("/log")
    public String log() {

        String requestId = RequestContextUtil.getRequestId();

        return "hello LoggingInterceptor " + requestId;
    }

}
