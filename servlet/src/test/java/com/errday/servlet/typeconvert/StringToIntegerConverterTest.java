package com.errday.servlet.typeconvert;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StringToIntegerConverterTest {

    @Test
    void convert() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer integer = converter.convert("123");
        Assertions.assertThat(integer).isEqualTo(123);
    }

}