package com.test.confxx.example.count;

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
@ThreadSafe //这个类是线程不安全的.结果不确定.
public class CountExample2 {
    //请求的总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

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
        log.info(count);
    }

    private static void add(){
        count.incrementAndGet(); //先做增加操作, 再获取返回结果.这个方法是线程安全的.
        // count.getAndIncrement(); 先获取返回结果,再做增加操作.
        /*
        unsafe : 使用了一个方法
        public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
        native : 表示是用底层代码实现的.不是java语言.

        方法的调用:
        public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        //对于当前这个对象, 如果当前值和底层的值是相同的, 就给他做一个增加.
        //因为当前线程在执行的时候, 可能会被其他线程更改, 所以要做这样一个判断

        return var5;
        }

        var1 : count这个对象
        var2 : 当前值
        var4 : 增加量

        var5 : 调用底层方法, 得到的底层当前的值.


         */
    }
}
