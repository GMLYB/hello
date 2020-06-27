package day6_24;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {

    /**
     * 获取构造器
     *
     */
    @Test
    public void test1(){

        Class personClass = Person.class;
        //getConstructors() : 获取当前运行时类中声明的public构造器
        Constructor[] constructors = personClass.getConstructors();
        for(Constructor c: constructors){
            System.out.println(c);
        }
        System.out.println();

        Constructor[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor c: declaredConstructors){
            System.out.println(c);
        }


    }
    /**
     * 获取运行时类的父类
     */
    @Test
    public void test2(){

        Class aClass = Person.class;
        Class superclass = aClass.getSuperclass();
        System.out.println(superclass);

    }

    /**
     * 获取运行时类的带泛型的父类
     */
    @Test
    public void test3(){
        Class<Person> aClass = Person.class;

        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println(genericSuperclass);
        //获取运行时类的带泛型的父类的泛型
        ParameterizedType paramType = (ParameterizedType)genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        for (Type type:actualTypeArguments){
            System.out.println(type.getTypeName());
        }
    }


    /**
     * 获取运行时类的接口
     */
    @Test
    public void test4(){

        Class aClass = Person.class;

        Class[] interfaces = aClass.getInterfaces();
        for (Class c:interfaces){
            System.out.println(c);
        }

        System.out.println();
        //获取父类接口
        //1.先获取父类
        Class superclass = aClass.getSuperclass();
        Class[] superclassInterfaces = superclass.getInterfaces();
        for (Class c: superclassInterfaces){
            System.out.println(c);
        }
    }

    /**
     * 获取运行时类所在的包
     */
    @Test
    public void test5(){
        Class<Person> aClass = Person.class;
        Package aPackage = aClass.getPackage();
        System.out.println(aPackage);
    }


    /**
     * 获取运行时类声明的注解
     */
    @Test
    public void test6(){
        Class aClass = Person.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation a : annotations){
            System.out.print(a);
        }
    }


}
