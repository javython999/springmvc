package com.errday.servlet.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String controllerName = handlerMethod.getBeanType().getName();
            String methodName = handlerMethod.getMethod().getName();
            AdminOnly adminOnly = handlerMethod.getMethodAnnotation(AdminOnly.class);

            if (adminOnly != null) {
                String userRole = request.getHeader("Role");
                if (userRole == null || !"ADMIN".equals(userRole)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access Denied: Admin Only");
                    return false;
                }
            }

            log.info("ControllerName {} MethodName {}", controllerName, methodName);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            String viewName = modelAndView.getViewName();

            if ("userProfile".equals(viewName)) {
                Map<String, Object> model = modelAndView.getModel();
                if (model.containsKey("username")) {
                    String username = model.get("username").toString();
                    String role;
                    if ("admin".equals(username)) {
                        role = "Administrator";
                    } else if("guest".equals(username)) {
                        role = "guest";
                    }else {
                        role = "User";
                    }
                    modelAndView.addObject("role", role);
                }
            }
        }


        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
