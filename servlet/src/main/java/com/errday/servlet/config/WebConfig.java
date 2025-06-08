package com.errday.servlet.config;

import com.errday.servlet.filter.MyAuthFilter;
import com.errday.servlet.resolver.CurrentArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentArgumentResolver());
    }

    @Bean
    public FilterRegistrationBean<MyAuthFilter> myAuthFilter() {
        FilterRegistrationBean<MyAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>(new MyAuthFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
