package com.lyb.filter;

import com.lyb.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            //提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            //出现异常，回滚事务
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            //抛出异常，给Tomcat统一展示错误界面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
