package com.errday.servlet.typeconvert.controller;

import com.errday.servlet.typeconvert.StringToUrlConverter;
import com.errday.servlet.typeconvert.UrlToStringConverter;
import com.errday.servlet.typeconvert.domain.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@RestController
public class ConverterController {

    private final StringToUrlConverter stringToUrlConverter = new StringToUrlConverter();
    private final UrlToStringConverter urlToStringConverter = new UrlToStringConverter();

    @GetMapping("/url")
    public String getUrl(@RequestParam String url) {
        Url result = stringToUrlConverter.convert(url);
        log.info("url: {}", result);

        return result.toString();
    }

    @PostMapping("/url")
    public String getUrl(@RequestParam Url url) {
        String result = urlToStringConverter.convert(url);
        log.info("url: {}", result);

        return result;
    }
}
