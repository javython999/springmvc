package com.errday.servlet.reflection.core;

import lombok.Getter;

@Getter
public class ReflectionExecutor {

    private final ReflectionFieldManager fieldManager;
    private final ReflectionMethodManager methodManager;

    public ReflectionExecutor(Class<?> clazz) {
        this.fieldManager = new ReflectionFieldManager(clazz);
        this.methodManager = new ReflectionMethodManager(clazz);
    }

}
