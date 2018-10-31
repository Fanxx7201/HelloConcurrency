package com.test.confxx.example.aqs01;

import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.*;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.aqs01
 * @ClassName:
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/30 14:08
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class CyclicBarrierExample2 {

    private static Log log = LogFactory.getLog(Test.class);
    //todo : 1. 给定值,设定线程数量. 线程数到达5的时候, 进行后续操作.
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() ->{
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error(e);
                }

            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info(threadNum);
        //抛异常时, 为了不影响下面的操作, 可以捕捉异常信息.
        try{
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException e){
            log.warn("BrokenBarrierException" + e);
        }

        //继续执行打印日志
        log.info("continue"+ threadNum);

    }
}
