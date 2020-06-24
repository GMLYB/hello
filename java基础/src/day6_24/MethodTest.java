package day6_24;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * 获取运行时类的方法结构
 *
 */

public class MethodTest {

    @Test
    public void test1(){

        Class aClass = Person.class;

        //getMethods(): 获取当前运行时类及其所有父类中声明的public权限的方法
        Method[] methods = aClass.getMethods();

        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println("*******************");

        //getDeclaredMethods(): 获取当前运行时类中声明的所有方法，（不包含父类中声明的方法）
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods){
            System.out.println(m);
        }
    }


    //权限修饰符 返回值类型  方法名(参数类型1 形参名1，...) throws xxxException{}
    @Test
    public void test2(){
        Class aClass = Person.class;
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods){
            //1.获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a: annotations){
                System.out.println(a);
            }

            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers())+"\t");

            //3.返回值类型
            System.out.print(m.getReturnType().getName()+"\t");

            //4.方法名
            System.out.print(m.getName()+"(");

            //5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if (parameterTypes ==null && parameterTypes.length == 0){

            }
            System.out.print(")");

            System.out.println();
        }

    }


}
