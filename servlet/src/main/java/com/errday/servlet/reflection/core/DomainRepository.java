package com.errday.servlet.reflection.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DomainRepository<T> {

    private final Map<Object, T> store = new HashMap<>();
    private final ReflectionExecutor executor;
    private final Class<T> clazz;
    private final String fieldName;

    public DomainRepository(Class<T> clazz,  String fieldName) {
        this.clazz = clazz;
        this.fieldName = fieldName;
        this.executor = new ReflectionExecutor(clazz);
    }

    public ReflectionExecutor getExecutor() {
        return executor;
    }

    public T save(Map<String, Object> data) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance = clazz.getDeclaredConstructor().newInstance();
        for (String key : data.keySet()) {
            executor.getFieldManager().setFieldValue(instance, key, data.get(key));
        }
        Object id = executor.getFieldManager().getFieldValue(instance, fieldName);
        store.put(id, instance);
        return instance;
    }

    public T getById(Long id) {
        return store.get(id);
    }

    public Object invoke(Object id, String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        T instance = store.get(id);
        if (instance != null) {
            return executor.getMethodManager().invoke(instance, methodName, args);
        }
        return null;
    }
}
