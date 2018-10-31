package com.test.confxx.example.aqs;

import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: fanxinxin
 * @create: 2018-10-28
 */
public class CountDownLauthExample1 {

    //创建给定的线程数_final明确不会被调整...
    private final static int threadCount = 200;

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) throws Exception {

        //定义线程池 , 调度多个线程.
        ExecutorService exec = Executors.newCachedThreadPool();

        //定义闭锁的实例
        final CountDownLatch countDownLauth = new CountDownLatch(threadCount);

        //for循环, 遍历当前的所有线程
        for(int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(()->{
                try{
                    test(threadNum);
                }catch (Exception e){
                    log.error(e);
                }finally{
                    //如果是为了保证无论什么情况, 即使出现异常, 也到减线程数的值的话, 就在finally中执行
                    //其他场景下, 如果满足条件就执行的话也可以.
                    countDownLauth.countDown(); //每次调用完, 线程数减1个
                }
            });
        }
        //调用完后,调用await方法,等调用完成后, 执行接下来的语句.
        //什么是调用完成? 就是countDownLauth一直减1, 直到减为0...
        countDownLauth.await();
        log.info("finish"); //finish在所有线程调用完成之后执行.
        exec.shutdown(); //线程池不再使用了.关闭节省资源.

    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info(threadNum); //输出当前的线程数
    }
}
