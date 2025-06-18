package com.errday.servlet.reflection.custom;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return BeanUtils.isSimpleProperty(parameter.getNestedParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String parameterName = parameter.getParameterName();
        String parameterValue = webRequest.getParameter(parameterName);
        Class<?> parameterType = parameter.getParameterType();

        if (StringUtils.hasText(parameterValue)) {
            if (parameterType.equals(String.class)) {
                return parameterValue;
            } else {
                WebDataBinder binder = new ExtendedServletRequestDataBinder(null, parameterValue);
                return binder.convertIfNecessary(parameterValue, parameterType, parameter);
            }
        }


        return null;
    }
}
