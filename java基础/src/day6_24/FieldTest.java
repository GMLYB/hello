package day6_24;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/*
    获取运行时类的属性结构

 */

public class FieldTest {

    @Test
    public void test1(){

        Class calss1 = Person.class;

        //获取属性结构
        //getFields(): 只能获取运行时类及其父类中声明为public访问权限的属性
        Field[] fields = calss1.getFields();
        for(Field f : fields){
            System.out.println(f);
        }

        System.out.println("*********************");

        //getDeclaredFields(): 获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
        Field[] declaredFields = calss1.getDeclaredFields();
        for(Field f : declaredFields){
            System.out.println(f);
        }
    }


    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){

        Class aClass = Person.class;

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f:declaredFields){
            //1.权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(modifiers+"\t");
            System.out.print(Modifier.toString(modifiers)+"\t");

            //2.数据类型
            Class type = f.getType();
            System.out.print(type+"\t");

            //3.变量名
            String name = f.getName();
            System.out.println(name);

        }

    }

}
