package com.theSunAndSnow.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
