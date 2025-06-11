package com.errday.servlet.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.ParseException;

@SpringBootTest
class KoreanCurrencyFormatterTest {

    @Test
    void currencyFormatter() throws ParseException {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        CustomFormatterRegistrar registrar = new CustomFormatterRegistrar();
        registrar.registerFormatters(conversionService);

        String currency = "₩1,000";
        Number parseCurrency = conversionService.convert(currency, Number.class);
        Assertions.assertThat(parseCurrency).isEqualTo(1000L);

        String printedCurrency = conversionService.convert(1000L, String.class);
        Assertions.assertThat(printedCurrency).isEqualTo("₩1,000");
    }
}