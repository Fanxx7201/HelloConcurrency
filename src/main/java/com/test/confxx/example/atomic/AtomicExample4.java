package com.test.confxx.example.atomic;

import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

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
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) {
        count.compareAndSet(0,2); //是0的话, 更新为2
        count.compareAndSet(0,1); //不执行
        count.compareAndSet(1,3);
        count.compareAndSet(2,4); //result = 4
        count.compareAndSet(3,5);
        log.info(count.get());
    }
}
