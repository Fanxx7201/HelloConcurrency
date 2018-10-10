package com.test.confxx.example.singlenton;

import com.test.confxx.annoations.NotRecommend;
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
@ThreadSafe //线程安全的懒汉模式.
@NotRecommend //不推荐的写法.synchronized带来额外的开销.
public class SingletonExample3 { //懒汉模式, 单例实例是在第一次使用时创建
    //要保证一个类只被初始化一次,一定要私有化构造函数.不允许外部随便创建对象
    private SingletonExample3(){

    }

    //返回单例对象,每次返回的是同一个对象.
    private static SingletonExample3 instance = null;

    //静态的工厂方法去获得单例对象
    //加上synchronized关键字.保证获取单实例在一个线程里边.所以是线程安全的.
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
