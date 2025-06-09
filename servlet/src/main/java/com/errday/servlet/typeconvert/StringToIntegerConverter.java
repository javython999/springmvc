package com.errday.servlet.typeconvert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        if (!StringUtils.hasText(source)) {
            throw new IllegalArgumentException("Input string is empty");
        }

        return Integer.parseInt(source);
    }
}
