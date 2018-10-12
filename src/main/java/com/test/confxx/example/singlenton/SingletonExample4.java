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
@NotThreadSafe //双重同步锁模式并不是线程安全的.
public class SingletonExample4 { //懒汉模式, 单例实例是在第一次使用时创建 ---->双重同步锁单例模式.
    //要保证一个类只被初始化一次,一定要私有化构造函数.不允许外部随便创建对象
    private SingletonExample4(){

    }
    //单线程情况下发生了什么
    //1.分配对象内存空间.
    //2.初始化对象
    //3.设置instance指向刚分配的内存.


    //多线程情况下, JVM和CPU优化, 发生了指令重排
    //1.分配对象内存空间.
    //3.设置instance指向刚分配的内存.
    //2.初始化对象



    //返回单例对象,每次返回的是同一个对象.
    private static SingletonExample4 instance = null;

    //静态的工厂方法去获得单例对象
    public static SingletonExample4 getInstance(){
        if(instance == null){ //双重检测机制
            synchronized (SingletonExample4.class){  //同步锁
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
