package com.test.confxx.example.atomic;

import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

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
public class AtomicExample5 {
    //AtomicInterFieldUpdater原子性去更新某一个类的实例中的某一个字段.
    //这个字段必须是volatile修饰的.不能是static --->count
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    public volatile int count = 100; //需要更新的字段名称
    private static Log log = LogFactory.getLog(Test.class);

    //定义实例 , example5实例中包含count值 = 100
    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {

        if (updater.compareAndSet(example5, 100, 120)){
            log.info(example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)){
            log.info(example5.getCount());
        } else {
            log.info(example5.getCount());
        }
    }

    public int getCount() {
        return count;
    }

}
