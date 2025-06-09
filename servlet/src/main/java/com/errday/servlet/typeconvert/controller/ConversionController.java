package com.errday.servlet.typeconvert.controller;

import com.errday.servlet.typeconvert.domain.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @GetMapping("/url")
    public Url getUrl(@RequestParam String url) {
        Url result = conversionService.convert(url, Url.class);
        log.info("result = {}", result);
        return result;
    }

    @PostMapping("/url")
    public String getUrl(@RequestParam Url url) {
        String result = conversionService.convert(url, String.class);
        log.info("url: {}", result);
        return result;
    }
}
