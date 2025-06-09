package com.errday.servlet.typeconvert.factory;

import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumType;

    public StringToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return Enum.valueOf(enumType, source.trim().toUpperCase());
    }
}
