package com.test.confxx.example.commonUnsafe;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.commonUnsafe
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/18 17:17
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class StringExample1 {

//简单的说
    //StringBuilder 是线程不安全的.  -- 方法内部定义局部变量, 堆栈封闭, 只有单线程可以操作, 已经是线程安全的 .性能也有所提升.
    //StringBuffer 是线程安全的.  原因 : 几乎所有的方法都加了synchronized 关键字-- 同一时间只有一个线程能调用.

}
