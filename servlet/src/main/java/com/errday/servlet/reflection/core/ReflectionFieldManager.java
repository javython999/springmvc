package com.errday.servlet.reflection.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionFieldManager {

    private final Map<String, Field> fieldMap = new HashMap<>();


    public ReflectionFieldManager(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
    }

    public Field getField(String fieldName) {
        return fieldMap.get(fieldName);
    }

    public void setFieldValue(Object target, String fieldName, Object value) throws IllegalAccessException {
        Field field = getField(fieldName);
        if (field != null) {
            field.set(target, value);
        }
    }

    public Object getFieldValue(Object target, String fieldName) throws IllegalAccessException {
        Field field = getField(fieldName);
        if (field != null) {
            return field.get(target);
        }

        return null;
    }
}
