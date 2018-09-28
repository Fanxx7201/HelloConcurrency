package com.test.confxx;

import com.test.confxx.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

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
 * 1.信号量semaphore ,是计数信号量.管理一系列的许可证.每个acquire方法阻塞，直到有一个许可证可以获得然后拿走一个许可证；
 * 每个release方法增加一个许可证，这可能会释放一个阻塞的acquire方法。然而，其实并没有实际的许可证这个对象，Semaphore只是维持了一个可获得许可证的数量。
 * Semaphore经常用于限制获取某种资源的线程数量。
 * 2.CountDownLatch :利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 * CountDownLatch可以阻塞线程,并保证线程在满足特定条件的时候,继续执行线程.
 */
@Slf4j
@NotThreadSafe //这个类是线程不安全的.结果不确定.
public class ConcurrencyTest {
    //请求的总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量semaphore_当前线程的信号量.
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁,希望所有的请求完之后,统计请求结果.
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        //放入请求,请求放入到线程池中.
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() ->{
                try{
                    semaphore.acquire(); //判断当前的线程能否被执行.如果达到一定并发数,这个add可能会临时被阻塞掉.
                    add();
                    semaphore.release(); //释放当前的进程.

                }catch (Exception e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();//执行完一次操作之后, 调用countDown.计数都会减1.
            });
        }
        countDownLatch.await();//await可以保证之前的countDown必须减为0,这个前提是所有的进程必须执行完.
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add(){
        count++; //这个是线程不安全的写法.
    }
}
