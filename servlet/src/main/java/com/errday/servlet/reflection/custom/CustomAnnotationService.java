package com.errday.servlet.reflection.custom;

import org.springframework.stereotype.Service;

@Service
public class CustomAnnotationService {

    @CustomAnnotation
    public String serviceMethod1(String param, long version) {
        return "Hello " + param + " " + version;
    }

    @CustomAnnotation
    public int serviceMethod2(int count) {
        return count * 2;
    }
}
