## SpringIoc组件

### 1 组件介绍

按其所承担的角色，可以划分为两类

物料组件

* Resource：配置文件配置的资源（例如配置文件.xml，或者被注解标记了类，.class等）
* BeanDefinition：通过资源解析产生的Bean定义对象
* PropertyEditor：记录各Bean的属性依赖
* 最终的Bean：它们就是加工流程中被加工、被消费的组件

加工设备组件

* ResourceLoader：资源加载器
* BeanDefinitionReader：Bean解析器
* BeanFactoryPostProcessor：Bean信息加工机器
* InstantiationStrategy：实例化对象机器
* BeanWrapper：Bean属性设置器，这些加工组件像是流水线上不同环节的加工设备，对物料组件进行加工处理

流程图

![image-20210106102030597](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20210106102030597.png)



### 2 自定义SpringIOC容器（基于注解的IOC容器）

* 新建Bean工厂：定义BeanMap用于存储Bean名称，类型和对象映射

* 新建容器对象

  * 定义BeanFactory，用于创建bean以及相关依赖，获取bean
  * 配置扫描路径，初始化容器时，扫描路径下所有有注解的class，实例化Bean存储到bean工厂中

* 定义注解：

  * MyComponent：作用在类上，表示一个Bean，让自定义容器管理

    * ```java
      @Target(ElementType.TYPE)
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      public @interface MyComponent {
          String value() default "";
      }
      ```

  * MyAutowired：作用在字段上，用于注入

    * ```java
      @Target(ElementType.FIELD)
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      public @interface MyAutowired {
      
      }
      ```





### 3 搭建过程

* 使用Idea创建一个简单maven项目
* 创建上面那两个注解（MyComponent 和 MyAutowired）
* 创建自定义工厂BeanFactory

```java
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
```

* 创建自定义容器 MySpringContext

```java
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
```



### 4 测试

* 创建一个dao

```java
@MyComponent
public class UserDao {
    public void UserAdd(){
        System.out.println("add user to Database");
    }
}
```

* 创建一个service

```java
@MyComponent
public class UserService {

    @MyAutowired
    UserDao userDao;

    public void addUser(){
        userDao.UserAdd();
        System.out.println("add user method");
    }
}
```

* 创建一个controller

```java
@MyComponent
public class UserController {

    @MyAutowired
    UserService userService;

    public void addUser(){
        userService.addUser();
        System.out.println("add user successful");
    }
}
```

* 创建一个main方法

```java
public class MySpringApplication {
    public static void main(String[] args) {
        MySpringContext context = new MySpringContext();
        try {
            System.out.println("开始初始化容器");
            long start = System.currentTimeMillis();
            context.initContext("com.lyb.ioc");
            System.out.println("容器初始化成功，花费时间为：" + (System.currentTimeMillis() - start));
            UserController userController = context.getBean("userController");
            userController.addUser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```



### 5 注意事项

* 存放项目的路径，最好不要有中文名称