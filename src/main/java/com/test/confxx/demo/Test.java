package com.test.confxx.demo;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.demo
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/9/20 17:37
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Test {

    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) {


        try {
            String i = null;
            i.length();

        }catch (Exception e){
            log.debug(e);
        }










        System.out.println("111111");
    }
    /*  报错: Error running 'Test': Cannot start process, the working directory 'D:\confxx\confxx' does not exist
        Run - Edit configurations.
        然后点击Application左边的向下箭头，在Configuration下找到Working directory，删除或者设置成合适directory就可以。
     */
}
