package com.test.confxx.example.syncContainer;

import com.test.confxx.annoations.NotThreadSafe;

import java.util.Iterator;
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
public class VectorExample3 {

    //单线程时, 如果用foreach ,迭代器时 , 不要做更新,或者是删除. 可以先做标记, 再删除. 多线程时出异常的概率更高.
    //可以用for循环来做操作, 是正常的.


    //遍历集合, 对指定元素做删除的操作.// java.util.ConcurrentModificationException 抛异常
    private static void test1(Vector<Integer> v1){
        for (Integer i : v1){
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }
    // java.util.ConcurrentModificationException 抛异常
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> iterator = v1.iterator();
        while (((Iterator) iterator).hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }

    }

    //成功的.
    private static void test3(Vector<Integer> v1){
        for (int i = 0; i < v1.size(); i++){
            if(v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }


    public static void main(String[] args) {
       Vector<Integer> vector = new Vector<>();
       vector.add(1);
       vector.add(2);
       vector.add(3);
       test1(vector);
    }



}
