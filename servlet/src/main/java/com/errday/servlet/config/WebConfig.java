package com.errday.servlet.config;

import com.errday.servlet.converter.StringToUserConverter;
import com.errday.servlet.filter.MyAuthFilter;
import com.errday.servlet.resolver.CurrentArgumentResolver;
import com.errday.servlet.typeconvert.StringToUrlConverter;
import com.errday.servlet.typeconvert.factory.StringToEnumConverterFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentArgumentResolver());
    }

    //@Bean
    public FilterRegistrationBean<MyAuthFilter> myAuthFilter() {
        FilterRegistrationBean<MyAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>(new MyAuthFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUrlConverter());
        registry.addConverter(new StringToUserConverter());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
