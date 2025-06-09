package com.errday.servlet.typeconvert;

import com.errday.servlet.typeconvert.domain.Url;
import org.springframework.core.convert.converter.Converter;

public class UrlToStringConverter implements Converter<Url, String> {

    @Override
    public String convert(Url url) {
        if (url == null) {
            throw new IllegalArgumentException("source is null");
        }
        return url.getProtocol() + "://" + url.getDomain() + ":" + url.getPort();
    }
}
