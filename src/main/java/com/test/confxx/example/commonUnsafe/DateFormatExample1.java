package com.test.confxx.example.commonUnsafe;

import java.text.SimpleDateFormat;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.commonUnsafe
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/18 17:34
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class DateFormatExample1 {

    //这种写法是错误的, 线程不安全. 调用parse方法的时候, 会各种抛异常
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static void main(String[] args) {
        //生命局部变量可以正常使用SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }



}
