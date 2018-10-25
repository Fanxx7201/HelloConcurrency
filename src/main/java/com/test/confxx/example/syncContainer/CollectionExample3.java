package com.test.confxx.example.syncContainer;

import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

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
@ThreadSafe //线程安全的.
public class CollectionExample3 {
    //请求的总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    //synchronizedMap生成的同步容器类, 做到了线程安全.
    public static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量semaphore_当前线程的信号量.
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁,希望所有的请求完之后,统计请求结果.
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        //放入请求,请求放入到线程池中.
        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() ->{
                try{
                    semaphore.acquire(); //判断当前的线程能否被执行.如果达到一定并发数,这个add可能会临时被阻塞掉.
                    update(count);
                    semaphore.release(); //释放当前的进程.

                }catch (Exception e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();//执行完一次操作之后, 调用countDown.计数都会减1.
            });
        }
        countDownLatch.await();//await可以保证之前的countDown必须减为0,这个前提是所有的进程必须执行完.
        executorService.shutdown();
        log.info(map.size());
    }

    private static void update(int i){
       map.put(i, i);

    }
}
