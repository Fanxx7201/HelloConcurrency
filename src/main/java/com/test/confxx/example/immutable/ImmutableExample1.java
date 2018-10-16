package com.test.confxx.example.immutable;

import com.google.common.collect.Maps;
import com.test.confxx.annoations.NotThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.immutable
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/15 11:34
 * <p>Copyright: Copyright (c) 2018</p>
 */
@NotThreadSafe
public class ImmutableExample1 {
    private static Log log = LogFactory.getLog(Test.class);
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap(); //guava包导入进来的.

    static{ //静态代码块
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //这里是重点的问题=============================================================>>>>>>>
        // a = 2; final修饰, 不可修改.编译时无法通过.
        // map = Maps.newHashMap(); 引用类型, 赋值之后, 不能指向另外一个对象.
        map.put(1, 3); //但是引用类型却允许修改里边的值. 这样容易会引发线程安全问题的.
        log.info(map.get(1));
    }

    private void test(final int a){ //final修饰的基本数据类型的参数, 也是不能修改的.
        //a = 1;
    }
}
