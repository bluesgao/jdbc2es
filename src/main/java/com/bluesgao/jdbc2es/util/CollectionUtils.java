package com.bluesgao.jdbc2es.util;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName: CollectionUtils
 * Desc: 集合常用工具类
 * Author: gaoxin11
 * Date: 2018/6/13 15:26
 **/
public class CollectionUtils {
    public static boolean isEmpity(Collection<?> collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return org.springframework.util.CollectionUtils.isEmpty(map);
    }
}
