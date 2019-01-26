package com.test.confxx.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: fanxinxin
 * @create: 2019-01-26
 */
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        //有序的, 单线程化的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 10; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task:" + index);
                }
            });
        }

        executorService.shutdown();
    }
}
