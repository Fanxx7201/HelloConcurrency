package com.test.confxx;

import com.test.confxx.example.threadLocal.RequstHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/17 14:20
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class HttpInterceptor extends HandlerInterceptorAdapter { //需要再启动类中定义
    private static Log log = LogFactory.getLog(HttpInterceptor.class);
    /**
     * @Description  请求之前做处理
     * @Date  2018/10/17
     * @Param [request, response, handler]
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        return true; //返回false,后边的接口就不处理了.
    }

    /**
     * @Description  请求之后做处理
     * @Date  2018/10/17
     * @Param [request, response, handler, ex]
     * @return void
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequstHolder.remove();
        log.info("afterCompletion");
        return;
    }


    /*
     * 拦截器提供可复写的方法
     * postHandle : 请求之后做处理(正常的情况)
     * afterCompletion : 请求之后做处理(正常和异常的情况)
     **/
}
