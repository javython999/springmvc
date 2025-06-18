package com.errday.servlet.reflection;

import java.lang.reflect.Method;

public class ReflectionExample4 {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.errday.servlet.domain.User");
        Object userInstance = clazz.getDeclaredConstructor().newInstance();
        // setName 메서드 호출
        Method setName = clazz.getDeclaredMethod("setUsername", String.class);
        setName.invoke(userInstance, "springmvc");
        // setAge 메서드 호출
        Method setAge = clazz.getDeclaredMethod("setEmail", String.class);
        setAge.invoke(userInstance, "test@test.com");
        // getName 메서드 호출
        Method getName = clazz.getDeclaredMethod("getUsername");
        getName.invoke(userInstance);
        // getAge 메서드 호출
        Method getAge = clazz.getDeclaredMethod("getEmail");
        getAge.invoke(userInstance);
    }
}
