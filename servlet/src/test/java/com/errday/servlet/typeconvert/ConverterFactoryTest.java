package com.errday.servlet.typeconvert;

import com.errday.servlet.typeconvert.factory.OrderColor;
import com.errday.servlet.typeconvert.factory.OrderStatus;
import com.errday.servlet.typeconvert.factory.StringToEnumConverterFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;

@SpringBootTest
public class ConverterFactoryTest {

    @Test
    void converterFactory() {
        StringToEnumConverterFactory factory = new StringToEnumConverterFactory();

        Converter<String, OrderColor> colorConverter = factory.getConverter(OrderColor.class);
        OrderColor color = colorConverter.convert("green");
        System.out.println("color = " + color);


        Converter<String, OrderStatus> statusConverter = factory.getConverter(OrderStatus.class);
        OrderStatus status = statusConverter.convert("processing");
        System.out.println("status = " + status);

        OrderColor nullColor = colorConverter.convert("   ");
        System.out.println("nullColor = " + nullColor);

        try {
            statusConverter.convert("UNKNOWN");
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}
