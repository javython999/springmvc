package com.errday.servlet.reflection;

import com.errday.servlet.domain.User;

import java.lang.reflect.Constructor;

public class ReflectionExample2 {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = User.class;
        // 생성자 호출
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        User user = (User) constructor.newInstance("springmvc", "test@test.com");
        System.out.println("Created User: " + user);
    }
}
