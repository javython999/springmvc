package com.errday.servlet.config;

import com.errday.servlet.annotationconverter.CustomCurrencyFormatterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*@Override
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
    }*/

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new CustomCurrencyFormatterFactory());
    }
}
