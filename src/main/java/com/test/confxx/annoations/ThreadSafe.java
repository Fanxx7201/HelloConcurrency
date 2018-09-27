package com.test.confxx.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.annoations
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/9/27 9:35
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Target(ElementType.TYPE) //注解作用的目标,type的意思是对类进行作用.
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {      //标记线程安全的类
    //定义注解要给默认值, 方便扩展.

    String value() default "";
}
