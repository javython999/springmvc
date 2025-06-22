package com.errday.servlet.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String accept = request.getHeader("Accept");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        try {

            if (accept.contains(MediaType.TEXT_HTML_VALUE)) {
                return new ModelAndView("error-view"); // 오류 화면으로 응답
            }
            if (accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
                Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("message", ex.getMessage());
                response.getWriter().write(errorDetails.toString());// JSON 형태로 응답
                return new ModelAndView();
            }
            if (ex instanceof RuntimeException) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());// WAS 로 오류 전파
                return new ModelAndView();
            }
            return null;// WAS 로 예외 전달
        } catch (Exception e) {
            return null;
        }
    }
}
