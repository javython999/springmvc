package com.errday.servlet.config;

import com.errday.servlet.annotationconverter.CustomCurrencyFormatterFactory;
import com.errday.servlet.exception.CustomHandlerExceptionResolver;
import com.errday.servlet.interceptor.CustomInterceptor1;
import com.errday.servlet.interceptor.CustomInterceptor2;
import com.errday.servlet.interceptor.LoggingInterceptor;
import com.errday.servlet.interceptor.MyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CustomInterceptor1 customInterceptor1;
    private final CustomInterceptor2 customInterceptor2;
    private final LoggingInterceptor loggingInterceptor;
    private final MyInterceptor myInterceptor;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/public/**")
                .order(1);

        registry.addInterceptor(customInterceptor2)
                .addPathPatterns("/login/**")
                .order(2);

        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/log/**");

        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/profile", "/admin");
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new CustomHandlerExceptionResolver());
    }
}
