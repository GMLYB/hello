package com.lyb.mvc.utils;


import com.lyb.mvc.anno.Enumration;
import com.lyb.mvc.dao.UserDao;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Eutil {
    public static boolean isNvlOrBlank(Object obj) {
        if (obj == null || obj.toString().trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static void checkRequireParam(String name, Object... strs) {
        for (Object str : strs) {
            if (isNvlOrBlank(str)) {
                throw new RuntimeException(String.format("param %s cannot empty.", name));
            }
        }
    }

    public static <T> void validate(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                NotBlank blankAnno = field.getAnnotation(NotBlank.class);
                NotNull nullAnno = field.getAnnotation(NotNull.class);
                Enumration enumAnno = field.getAnnotation(Enumration.class);
                Size sizeAnno = field.getAnnotation(Size.class);
//                Min minAnno = field.getAnnotation(Min.class);
//                Max maxAnno = field.getAnnotation(Max.class);

                //获取属性值
                field.setAccessible(true);
                //获取界面传过来的值
                Object value = field.get(obj);
                //实体类中的属性的名称
                String name = field.getName();
                System.out.println(name);

                //判断 NotBlank 注解
                if (blankAnno != null) {
                    checkRequireParam(name, value);
                }
                //判断 NotNull 注解
                if (nullAnno != null) {
                    if (value == null) {
                        throw new RuntimeException(String.format("param %s cannot be null", name));
                    }
                }
//                //判断 Max 注解
//                if (maxAnno != null) {
//                    //且为Integer类型
//                    if (value instanceof Integer) {
//                        int tmpval = (Integer) value;
//                        if (tmpval > maxAnno.value()) {
//                            throw new RuntimeException(String.format("param %s is too large.", name));
//                        }
//                    } else {
//                        throw new RuntimeException(String.format("The value type of parameter %s is not correct.", name));
//                    }
//                }
//                //判断 Min 注解
//                if (minAnno != null) {
//                    //且为Integer类型
//                    if (value instanceof Integer) {
//                        int tmpval = (Integer) value;
//                        if (tmpval < minAnno.value()) {
//                            throw new RuntimeException(String.format("param %s is too small.", name));
//                        }
//                    } else {
//                        throw new RuntimeException(String.format("The value type of parameter %s is not correct.", name));
//                    }
//                }
                if (sizeAnno != null) {
                    //为Integer类型
                    if (value instanceof Integer) {
                        int tmpval = (Integer) value;
                        if (tmpval < sizeAnno.min()) {
                            throw new RuntimeException(String.format("param %s is too small.", name));
                        } else if (tmpval > sizeAnno.max()) {
                            throw new RuntimeException(String.format("param %s is too large.", name));
                        }
                    } else {
                        throw new RuntimeException(String.format("The value type of parameter %s is not correct.", name));
                    }
                }
                if (enumAnno != null) {
                    String[] options = enumAnno.options();
                    List<String> list = null;
                    //先判断是options还是value
                    if (options.length == 0) {
                        //不是options，就获取value值
                        String enumName = enumAnno.value();
                        //看看注解上的 value是否为空
                        if (!isNvlOrBlank(enumName)) {
                            //若不是空，就通过注解上的 value 获取相对应的list
                            list = UserDao.getEnumList(enumName);
                        }
                    } else {
                        //若为options，就把options作为list
                        list = Arrays.asList(options);
                    }
                    //对list进行非空判断，为空就不用进行下去了
                    if (CollectionUtils.isEmpty(list)) {
                        continue;
                    }
                    //强制转换为String
                    String val = getStr(value);
                    if (!list.contains(val)) {
                        throw new RuntimeException(String.format("param %s value not exists.", name));
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStr(Object obj) {
        return obj.toString();
    }
}
