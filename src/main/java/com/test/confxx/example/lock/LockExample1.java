package com.test.confxx.example.lock;

import com.test.confxx.annoations.NotThreadSafe;
import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/9/27 17:07
 * <p>Copyright: Copyright (c) 2018</p>
 */
//@Slf4j
@ThreadSafe
public class LockExample1 {
    //请求的总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    //定义锁的实例
    private final static Lock lock = new ReentrantLock();

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() ->{
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();

                }catch (Exception e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}" + count);
    }

    private static void add(){
        lock.lock();
        try{
            count++;
        } finally {
            lock.unlock();
        }
    }
}
