package com.errday.servlet.typeconvert.conversion;

import com.errday.servlet.typeconvert.StringToUrlConverter;
import com.errday.servlet.typeconvert.UrlToStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Slf4j
@Configuration
public class ConversionServiceConfig {

    @Bean
    public DefaultConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToUrlConverter());
        conversionService.addConverter(new UrlToStringConverter());
        return conversionService;
    }
}
