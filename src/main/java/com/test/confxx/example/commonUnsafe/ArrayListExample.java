package com.test.confxx.example.commonUnsafe;

import com.test.confxx.annoations.NotThreadSafe;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.commonUnsafe
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/19 17:48
 * <p>Copyright: Copyright (c) 2018</p>
 */
@NotThreadSafe
public class ArrayListExample {

    //ArrayList 和HashSet , HashMap 都是有线程安全问题的.

    //多个线程并发访问这些容器, 就会有线程安全问题!
    //ArrayList==>Vector  --ArrayList的线程安全的类
    //HashMap ==> HashTable

    //他们对应的线程安全的类, 不只是一个.以后会具体介绍.













}
