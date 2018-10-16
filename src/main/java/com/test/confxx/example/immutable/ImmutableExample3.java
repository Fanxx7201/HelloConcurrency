package com.test.confxx.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.test.confxx.demo.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ProjectName: confxx
 * @Package: com.test.confxx.example.immutable
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/10/15 13:45
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class ImmutableExample3 {

    private static Log log = LogFactory.getLog(Test.class);
    private final static ImmutableList list = ImmutableList.of(1, 2, 3); //of的参数可以有更多.可以一直写下去
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();

    public static void main(String[] args) {
        list.add(4); //抛异常, 不允许修改.ImmutableList可以写成List ,也一样不可以修改.
        set.add(4); //同样抛异常.
        map.put(1, 4);
    }
}
