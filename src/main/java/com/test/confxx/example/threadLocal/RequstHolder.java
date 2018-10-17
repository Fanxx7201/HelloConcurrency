package com.test.confxx.example.threadLocal;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.threadLocal
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/16 16:29
 * <p>Copyright: Copyright (c) 2018</p>
 */

public class RequstHolder { //存储希望保存到threadLocal中的信息

    //每个请求都对应服务器的一个线程, 线程间的隔离, 是线程在被后端服务器进行处理的时候,
    //通过filter可以取出来当前的用户.把数据存储到threadLocal里边.
    //当前线程, 在被service等类进行处理的时候, 很可能就需要取出来当前的用户.
    //这个时候 ,我们就可以从threadLocal里边随时随地取出来我们保存进去的值.
    //不这样的话, 我们需要从request中取出用户, 从controller开始去, 一直传递下去.甚至到util类.


    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>(); //记录当前线程的id

    /**
     * @Description  请求到后端服务器, 但是没有进行实际处理的时候.调用该方法, 写入相关信息
     * todo: filter, 在filter中先拦住对应的url.前台访问url时,在filter中把相关信息写入进去. -- 1.HttpFilter类, 2.启动类中配置
     * @Date  2018/10/17
     * @Param [id]
     * @return void
     **/
    public static void add(Long id){
        requestHolder.set(id); //取出当前线程的id
    }

    public static Long getId(){ //获取当前线程的id
        return requestHolder.get();
    }
    /**
     * @Description  todo: HttpInterceptor中做移除.
     * @Date  2018/10/17
     * @Param []
     * @return void
     **/

    //不做移除,数据永远不会释放掉.内存泄露.
    //这个requestHolder会永远伴随项目, 只有项目重新启动, 内存才会被释放.
    public static void remove(){
        requestHolder.remove();
    }

}
