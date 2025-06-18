package com.errday.servlet.reflection.custom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.InvocableHandlerMethod;

import java.lang.reflect.Method;

@Component
public class DynamicMethodInvoker {

    public void invoke(HttpServletRequest req, HttpServletResponse resp, Object handler, Method method) throws Exception {
        InvocableHandlerMethod invocableHandlerMethod = new InvocableHandlerMethod(handler, method);

        HandlerMethodArgumentResolverComposite resolverComposite = new HandlerMethodArgumentResolverComposite();
        resolverComposite.addResolver(new CustomHandlerMethodArgumentResolver());

        invocableHandlerMethod.setHandlerMethodArgumentResolvers(resolverComposite);

        ServletWebRequest servletWebRequest = new ServletWebRequest(req, resp);
        Object result = invocableHandlerMethod.invokeForRequest(servletWebRequest, null);
        resp.getWriter().write("result : " + result);
    }
}
