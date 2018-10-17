package com.test.confxx;


import com.test.confxx.demo.Test;
import com.test.confxx.example.threadLocal.RequstHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/17 13:40
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class HttpFilter implements Filter { //需要在启动类中配置, 这个filter可以被启动.
    private static Log log = LogFactory.getLog(HttpFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //1.第一步, 强转, 将servletRequest强转为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //打印当前路径的请求
        log.info(Thread.currentThread().getId() +  request.getServletPath());

        //2.将当前的thread的id存入
        RequstHolder.add(Thread.currentThread().getId());

        //3.filter不想拦截住这个请求, 只是想单独做数据处理. 需要调用方法. 这样保证拦截器处理完, 这个请求还会接着被处理.
        filterChain.doFilter(servletRequest, servletResponse);









    }

    @Override
    public void destroy() {

    }
}
