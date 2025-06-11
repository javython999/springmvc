package com.errday.servlet.formatter;

import com.errday.servlet.typeconvert.StringToUrlConverter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class CustomFormatterRegistrar implements FormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatter(new KoreanCurrencyFormatter());
        registry.addConverter(new StringToUrlConverter());
    }
}
