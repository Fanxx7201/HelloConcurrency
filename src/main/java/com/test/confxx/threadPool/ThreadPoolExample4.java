package com.test.confxx.threadPool;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: fanxinxin
 * @create: 2019-01-26
 */
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        //这里已经有了调度的概念
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        /*
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule run");
            }
        }, 3, TimeUnit.SECONDS); //延迟3秒执行
        */

        //以指定的速率去执行任务
        //scheduledExecutorService.scheduleAtFixedRate();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule run");
            }
        },1, 3, TimeUnit.SECONDS); //延迟1秒, 然后每隔3秒去执行一次




        //以指定的延迟去执行任务
        //scheduledExecutorService.scheduleWithFixedDelay();

        //任务是每隔3秒执行一次, 所以我们不能调用关闭的方法
        //scheduledExecutorService.shutdown();

        //演示timer作延迟
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer run");
            }
        }, new Date(), 5 * 1000);//执行之后, 间隔5秒继续执行

    }
}
