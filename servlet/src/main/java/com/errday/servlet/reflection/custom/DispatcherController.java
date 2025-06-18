package com.errday.servlet.reflection.custom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class DispatcherController {

    private final CustomMethodExtractor methodExecutor;
    private final CustomAnnotationService customAnnotationService;
    private final DynamicMethodInvoker dynamicMethodInvoker;

    @GetMapping("/dynamic/invoke")
    public void invokeDynamicMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Set<Method> methods = methodExecutor.getAnnotationMethods(CustomAnnotationService.class);

        String methodName = request.getParameter("method");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                dynamicMethodInvoker.invoke(request, response, customAnnotationService, method);
                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write("Method not found");
    }
}
