package com.lyb.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    public static <T> T copyParamToBean(Map value, T bean){
        try {
            // 把所有参数都注入到 Bean 对象中
            BeanUtils.populate(bean,value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
