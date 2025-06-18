package com.errday.servlet.reflection;

import com.errday.servlet.domain.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionExample3 {

    public static void main(String[] args) throws Exception {
        User user = new User();
        Class<?> clazz = user.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // private 필드 접근 허용
            System.out.println("Field Name: " + field.getName());
            System.out.println("Field Value: " + field.get(user));
            if (field.getType() == String.class) {
                field.set(user, "springmvc ");
            }
        }
        System.out.println("Updated Name: " + clazz.getDeclaredField("username").get(user));
    }
}
