package com.errday.servlet.annotationconverter;

import org.springframework.format.Formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CustomCurrencyFormatter implements Formatter<BigDecimal> {

    private final String pattern;
    private final int decimalPlaces;

    public CustomCurrencyFormatter(String pattern, int decimalPlaces) {
        this.pattern = pattern;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        Number number = decimalFormat.parse(text);
        return BigDecimal.valueOf(number.doubleValue())
                .setScale(decimalPlaces, RoundingMode.HALF_UP);
    }

    @Override
    public String print(BigDecimal number, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        formatter.setMaximumFractionDigits(decimalPlaces);
        return formatter.format(number);
    }
}
