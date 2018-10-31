package com.test.confxx.example.aqs01;

import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.aqs01
 * @ClassName:
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/30 14:08
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class CyclicBarrierExample3 {

    private static Log log = LogFactory.getLog(Test.class);
    //可以在这里指定一个runnable, 线程到达屏障之前, 优先执行runnable .
    private static CyclicBarrier barrier = new CyclicBarrier(5, () ->{
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() ->{
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error(e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info(threadNum);
        barrier.await();
        //继续执行打印日志
        log.info("continue"+ threadNum);
    }
}
