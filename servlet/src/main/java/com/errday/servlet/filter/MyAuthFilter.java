package com.errday.servlet.filter;


import com.errday.servlet.domain.LoginUser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class MyAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        LoginUser loginUser = new LoginUser(1L, "springMvc", "a@a.com");
        req.setAttribute("loginUser", loginUser);

        chain.doFilter(request, response);
    }
}
