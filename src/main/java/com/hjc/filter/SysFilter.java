package com.hjc.filter;

import com.hjc.pojo.User;
import com.hjc.tools.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
