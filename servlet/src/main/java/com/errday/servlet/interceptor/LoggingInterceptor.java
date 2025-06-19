package com.errday.servlet.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        RequestContextUtil.setRequestId(requestId);
        log.info("Request ID {} : PreHandle {} {}", requestId, request.getMethod(), request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestId = RequestContextUtil.getRequestId();
        log.info("Request ID {} : PreHandle {} {}", requestId, request.getMethod(), request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String requestId = RequestContextUtil.getRequestId();

        if (ex != null) {
            log.error("Request ID {} : AfterCompletion - error {}", requestId, ex.getMessage());
        } else {
            log.info("Request ID {} : AfterCompletion", requestId);
        }

        RequestContextUtil.clearRequestId();


    }
}
