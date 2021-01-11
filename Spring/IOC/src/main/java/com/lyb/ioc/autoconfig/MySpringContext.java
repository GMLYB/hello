package com.lyb.ioc.autoconfig;


import com.lyb.ioc.annotion.MyAutowired;
import com.lyb.ioc.annotion.MyComponent;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySpringContext {

    private MyBeanFactory beanFactory = new MyBeanFactory();


    /**
     * 初始化容器
     *
     * @param path
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void initContext(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        path = path.replace(".", "/");
        ClassLoader classLoader = MySpringContext.class.getClassLoader();
        URL url = classLoader.getResource(path);
        File file = new File(url.getPath());
        List<File> clzList = new ArrayList<File>();
        getFileList(file, clzList);
        List<Class<?>> classList = new ArrayList<Class<?>>();
        for (File clz : clzList) {
            String apath = clz.getAbsolutePath();
            apath = apath.replace("\\", "/");
            int pre = apath.indexOf(path);
            String clzName = apath.substring(pre);
            clzName = clzName.replace(".class", "").replace("/", ".");
            Class<?> loadClass = classLoader.loadClass(clzName);
            // 扫描类上面的MyComponent注解
            MyComponent annotation = loadClass.getAnnotation(MyComponent.class);
//            // 扫描类上面的MyService注解
//            UdaService serviceAnno = loadClass.getAnnotation(UdaService.class);
            if (annotation != null) {
                String name = annotation.value();
                beanFactory.createBean(loadClass, name);
                //全部都收集起来，后面判断是否有依赖关系
                classList.add(loadClass);
            }

        }
        for (Class<?> loadClass : classList) {
            // 注入
            injection(loadClass);
        }
    }


    private void injection(Class<?> loadClass) throws IllegalAccessException {
        // 扫描字段上的注解，进行注入
        Field[] fields = loadClass.getDeclaredFields();
        for (Field field : fields) {
            MyAutowired fieldAnno = field.getAnnotation(MyAutowired.class);
            if (fieldAnno != null) {
                beanFactory.injectionField(loadClass, field);
            }
        }
    }

    public <T> T getBean(Class<?> clz) {
        return beanFactory.getBean(clz);
    }

    public <T> T getBean(String name) {
        return beanFactory.getBean(name);
    }

    public Map<String, Object> getBeans() {
        return beanFactory.getBeans();
    }

    /**
     * 扫描类
     *
     * @param file
     * @param clzList
     */
    private void getFileList(File file, List<File> clzList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                getFileList(f, clzList);
            }
        } else {
            clzList.add(file);
        }
    }

}
