package com.errday.servlet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        String[] data = source.split(":");
        if (data.length != 2) {
            throw new IllegalArgumentException("Invalid user data");
        }

        return new User(data[0].trim(), data[1].trim());
    }
}
