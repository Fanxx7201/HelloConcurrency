package com.test.confxx.example.commonUnsafe;


import com.test.confxx.annoations.ThreadSafe;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.commonUnsafe
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/18 17:46
 * <p>Copyright: Copyright (c) 2018</p>
 */
@ThreadSafe
public class JodaTimeExample {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");


    public static void main(String[] args) {
        DateTime.parse("20180208", dateTimeFormatter).toDate();
    }


}
