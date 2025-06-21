package com.errday.servlet.exception;

import jakarta.servlet.ServletException;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatReactiveWebServerFactory> {

    @Override
    public void customize(TomcatReactiveWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/exception/401"));
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/exception/404"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/exception/500"));
        factory.addErrorPages(new ErrorPage(ServletException.class, "/exception/exception"));
    }
}
