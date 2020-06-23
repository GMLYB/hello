package day6_23;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 *
 */

public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> class1 = Person.class;
        /*
            newInstance():调用此方法，创建对应的运行时类的对象。内部调用了空参的构造器
            要想此方法正常创建运行时类的对象，要求：
                1.运行时类必须提供空参构造器
                2.空参构造器的访问权限的够，通常设置为public

            在Javabean中要求提供一个public的空参构造器。原因：
                1.便于通过反射，创建运行时类的对象
                2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器

         */
        Person person = class1.newInstance();//调用了空参构造器
        System.out.println(person);

    }

    //体会反射的动态性
    @Test
    public void test2(){

        int num = new Random().nextInt(3);//0,1,2
        String calssPath = "";
        switch (num){
            case 0:
                calssPath = "java.util.Date";
                break;
            case 1:
                calssPath = "java.lang.Object";
                break;

            case 2:
                calssPath = "day6_23.Person";
                break;
        }
        try {
            Object obj = getInstance(calssPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Object getInstance(String classPath) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

}
