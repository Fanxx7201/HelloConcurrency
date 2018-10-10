package com.test.confxx.example.singlenton;

import com.test.confxx.annoations.NotThreadSafe;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.singlenton
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/10 10:40
 * <p>Copyright: Copyright (c) 2018</p>
 */
@NotThreadSafe
public class SingletonExample1 { //懒汉模式, 单例实例是在第一次使用时创建
    //要保证一个类只被初始化一次,一定要私有化构造函数.不允许外部随便创建对象
    private SingletonExample1(){

    }

    //返回单例对象,每次返回的是同一个对象.
    private static SingletonExample1 instance = null;

    //静态的工厂方法去获得单例对象
    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
