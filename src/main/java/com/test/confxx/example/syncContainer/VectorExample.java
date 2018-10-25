package com.test.confxx.example.syncContainer;

import com.test.confxx.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.commonUnsafe
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/22 9:33
 * <p>Copyright: Copyright (c) 2018</p>
 */
@NotThreadSafe
public class VectorExample {
    //代码运行会数组越界异常. 虽然remove , get都是线程安全的, 但是调用顺序的关系, 可能是先remove掉了, 再get肯定就报错了.
    //因为调用顺序的差异,不同线程可能触发线程不安全的问题
    //为了保证线程安全, 要在方法调用端做一些额外的措施.

    private static Vector<Integer> vector = new Vector<>(); //同步容器Vector对象

    public static void main(String[] args) {
        while(true){
            for(int i = 0; i < 10; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                  @Override
                  public void run(){
                      for(int i = 0; i < vector.size(); i++){
                          vector.remove(i); //remove操作是有sync修饰的.
                      }
                  }
            };

            Thread thread2 = new Thread(){
                  @Override
                  public void run(){
                      for (int i = 0; i < vector.size(); i++){
                          vector.get(i); //get越界是remove引起的.
                      }
                  }
            };
            thread1.start();
            thread2.start();
        }
    }



}
