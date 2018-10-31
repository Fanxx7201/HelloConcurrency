package com.test.confxx.example.aqs;

import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: fanxinxin
 * @create: 2018-10-28
 */
public class SemaphoreExample1 {

    //创建给定的线程数_final明确不会被调整...
    private final static int threadCount = 20;

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) throws Exception {

        //定义线程池 , 调度多个线程.
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3); //这里要给定允许的并发数.也就是20. 每一秒只执行20个. 一共是200个.

        //for循环, 遍历当前的所有线程
        for(int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(()->{
                try{
                    semaphore.acquire(); //拿到一个许可.
                    test(threadNum); //这个方法是需要做并发控制的方法...拿到许可之后执行
                    semaphore.release(); //释放许可.
                }catch (Exception e){
                    log.error(e);
                }finally{
                    //如果是为了保证无论什么情况, 即使出现异常, 也到减线程数的值的话, 就在finally中执行
                    //其他场景下, 如果满足条件就执行的话也可以.
                    //countDownLauth.countDown(); //每次调用完, 线程数减1个
                }
            });
        }
        //调用完后,调用await方法,等调用完成后, 执行接下来的语句.
        //什么是调用完成? 就是countDownLauth一直减1, 直到减为0...
        //countDownLauth.await();
        log.info("finish"); //finish在所有线程调用完成之后执行.
        exec.shutdown(); //线程池不再使用了.关闭节省资源.

    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info(threadNum); //输出当前的线程数
    }
}
