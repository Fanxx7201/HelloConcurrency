package com.test.confxx.example.singlenton;

import com.test.confxx.annoations.NotThreadSafe;
import com.test.confxx.annoations.ThreadSafe;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.singlenton
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/10 10:40
 * <p>Copyright: Copyright (c) 2018</p>
 */
@ThreadSafe
public class SingletonExample2 { //饿汉模式, 单例实例是在类装载的时候创建. 不会带来资源的浪费.饿汉模式是线程安全的.

    //要保证一个类只被初始化一次,一定要私有化构造函数.不允许外部随便创建对象
    private SingletonExample2(){

    }

    //返回单例对象,每次返回的是同一个对象.
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法去获得单例对象
    public static SingletonExample2 getInstance(){

        return instance;
    }
}
