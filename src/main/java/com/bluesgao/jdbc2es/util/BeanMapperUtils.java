package com.bluesgao.jdbc2es.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BeanMapperUtils
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 12:43
 **/
public class BeanMapperUtils {
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
