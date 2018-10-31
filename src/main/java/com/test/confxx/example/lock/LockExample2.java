package com.test.confxx.example.lock;

import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx
 * @ClassName: ${TYPE_NAME}
 * @Description: 代码场景:封装内部的map , 不希望把函数暴露给别人. 又避免发生并发问题.
 * 实现的是悲观读取. 写入时, 不允许任何读的操作. 有问题的情况, 就是你项目读操作很多, 写的很少.
 * 就可能会使线程遭遇饥饿问题, 就是一直有读的操作, 我写的操作就一直无法执行.
 * (用的很少, 注意场景).
 * @Author: fanxx
 * @CreateDate: 2018/9/27 17:07
 * <p>Copyright: Copyright (c) 2018</p>
 */
//@Slf4j
@ThreadSafe
public class LockExample2 {

    private final Map<String, Data> map = new TreeMap<>();
    //定义锁的实例
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Log log = LogFactory.getLog(Test.class);

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        //读锁, 做实际的读取请求
        readLock.lock();
        try{
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value){
        //写操作, 加写锁
        writeLock.lock();
        try{
            return map.put(key, value);
        } finally {
            readLock.unlock();
        }
    }
    class Data{
    }
}
