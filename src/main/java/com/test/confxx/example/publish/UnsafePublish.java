package com.test.confxx.example.publish;

import com.test.confxx.annoations.NotThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.publish
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/9 16:28
 * <p>Copyright: Copyright (c) 2018</p>
 */
@NotThreadSafe
public class UnsafePublish {

    private static Log log = LogFactory.getLog(Test.class);

    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return  states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish(); //发布了这个类的实例.
        log.info(Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d"; //这样,通过public方法,可以任意的去更改数组中的值.
        //我们无法保证这个对象是否被修改过.所以是线程不安全的.
        log.info(Arrays.toString(unsafePublish.getStates()));
    }
}
