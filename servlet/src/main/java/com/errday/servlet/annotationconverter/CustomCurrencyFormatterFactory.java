package com.errday.servlet.annotationconverter;

import com.errday.servlet.annotation.CustomCurrencyFormat;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.math.BigDecimal;
import java.util.Set;

public class CustomCurrencyFormatterFactory implements AnnotationFormatterFactory<CustomCurrencyFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Set.of(BigDecimal.class);
    }

    @Override
    public Printer<?> getPrinter(CustomCurrencyFormat annotation, Class<?> fieldType) {
        return new CustomCurrencyFormatter(annotation.pattern(), annotation.decimalPlaces());
    }

    @Override
    public Parser<?> getParser(CustomCurrencyFormat annotation, Class<?> fieldType) {
        return new CustomCurrencyFormatter(annotation.pattern(), annotation.decimalPlaces());
    }
}
