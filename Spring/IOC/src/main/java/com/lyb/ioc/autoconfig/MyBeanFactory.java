package com.lyb.ioc.autoconfig;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyBeanFactory {
    // 类名 , 实例化后的对象
    private Map<String, Object> beanMap = new LinkedHashMap<String, Object>();

    /**
     * 创建对象
     *
     * @param clz
     * @param name
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object createBean(Class<?> clz, String name) throws InstantiationException, IllegalAccessException {
        Object instance = clz.newInstance();
        //name为注解上定义的名字
        if (name == null || name.length() == 0) {
            //注解上没有输入名字，就用首字母小写作为key值传入
            beanMap.put(lowerCase(clz.getSimpleName()), instance);
        } else {
            beanMap.put(name, instance);
        }
        //再用长类名存储一份 下面有两个方法，一个是通过长类型获得对象，一个是通过name获得到对象
        beanMap.put(clz.getName(), instance);
        return instance;
    }

    /**
     * 第一个字母转换为小写
     * @param str
     * @return
     */
    private String lowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 通过长类名获得对象
     * @param clz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<?> clz) {
        return (T) beanMap.get(clz.getName());
    }

    /**
     * 通过自定义名称获得对象
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) beanMap.get(name);
    }

    /**
     * 注入对象
     *
     * @param clz
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void injectionField(Class<?> clz, Field field) throws IllegalArgumentException, IllegalAccessException {
        Class<?> type = field.getType();
        Object curObj = beanMap.get(clz.getName());
        Object injectObj = beanMap.get(type.getName());
        // 设置忽略权限
        field.setAccessible(true);
        field.set(curObj, injectObj);
    }

    public Map<String, Object> getBeans() {
        return beanMap;
    }
}
