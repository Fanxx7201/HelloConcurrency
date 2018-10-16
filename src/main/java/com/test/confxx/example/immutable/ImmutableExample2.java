package com.test.confxx.example.immutable;

import com.google.common.collect.Maps;

import com.test.confxx.annoations.ThreadSafe;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.Map;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.immutable
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/15 11:34
 * <p>Copyright: Copyright (c) 2018</p>
 */
@ThreadSafe
public class ImmutableExample2 {
    private static Log log = LogFactory.getLog(Test.class);

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static{ //静态代码块
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

        map.put(1, 3); //抛异常. 这样是不允许的.Collections.unmodifiableMap 是不可以被修改的.
        log.info(map.get(1));
    }


}
