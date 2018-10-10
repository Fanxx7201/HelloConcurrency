package com.test.confxx.example.publish;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.publish
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/9 17:24
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Escape { //对象逸出.一个对象还没构造完成时, 就使它被其他线程所见.

    private static Log log = LogFactory.getLog(Test.class);

    private int thisCanBeEscape = 0; //这个变量可以逃逸到对象外, 被其他的线程看到并做更改.

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info(Escape.this.thisCanBeEscape); //对象还没有正式被发布之前就被引用.有不安全的隐患
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
