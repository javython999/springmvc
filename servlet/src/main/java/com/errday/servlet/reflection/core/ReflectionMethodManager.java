package com.errday.servlet.reflection.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMethodManager {

    private final Map<String, Method> methodMap = new HashMap<>();


    public ReflectionMethodManager(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            methodMap.put(method.getName(), method);
        }
    }

    public Method getMethod(String methodName) {
        return methodMap.get(methodName);
    }

    public Object invoke(Object target, String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        Method method = getMethod(methodName);
        if (method != null) {
            return method.invoke(target, args);
        }

        return null;
    }
}
