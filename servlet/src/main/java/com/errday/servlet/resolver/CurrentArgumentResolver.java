package com.errday.servlet.resolver;

import com.errday.servlet.annotation.CurrentUser;
import com.errday.servlet.domain.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(CurrentUser.class);
        boolean isLoginType = LoginUser.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && isLoginType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        Object loginUser = request.getAttribute("loginUser");

        if (loginUser instanceof LoginUser) {
            return loginUser;
        }

        return null;
    }
}
