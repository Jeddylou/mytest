package cn.itcast.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo7 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter7执行了.....");
        chain.doFilter(req, resp);
        System.out.println("filterdemo7回来.....");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
