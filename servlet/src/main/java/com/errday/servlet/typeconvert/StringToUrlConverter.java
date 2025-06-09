package com.errday.servlet.typeconvert;

import com.errday.servlet.typeconvert.domain.Url;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToUrlConverter implements Converter<String, Url> {

    @Override
    public Url convert(String source) {
        if (!StringUtils.hasText(source)) {
            throw new IllegalArgumentException("source should not be empty");
        }
        String[] parts = source.split("://");
        String protocol = parts[0];

        String[] parts2 = parts[1].split(":");
        String domain = parts2[0];
        int port = Integer.parseInt(parts2[1]);
        return new Url(protocol, domain, port);
    }
}
