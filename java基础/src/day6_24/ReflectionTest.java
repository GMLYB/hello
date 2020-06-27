package day6_24;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 */

public class ReflectionTest {

    /*
        不需要掌握。只能操作public
     */

    @Test
    public void testField() throws Exception {
        Class<Person> aClass = Person.class;
        //创建运行时类的对象
        Person person = aClass.newInstance();

        //获取指定的属性：要求运行时类中的属性为public
        //因为public比较少，因此不采用此方法
        Field id = aClass.getField("id");
//        Field id = aClass.getField("name");//private

        /**
         * 设置当前属性的值
         * set():参数1,：指明设置哪个对象的属性 参数2：将此属性值设置为多少
         */
        id.set(person, 1001);

        /*
        get():参数1：获取哪个对象当前的属性值
         */
        int pid = (int) id.get(person);
        System.out.println("pid = " + pid);

    }

        /*
          如何操作运行时类中指定的属性 -- 需要掌握
         */
    @Test
    public void testField1() throws Exception {
        Class<Person> aClass = Person.class;
        //创建运行时类对象
        Person person = aClass.newInstance();

        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = aClass.getDeclaredField("name");

        //2.setAccessible() 保证当前属性是可以访问的
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        name.set(person,"Tom");
        String pname = String.valueOf(name.get(person));
        System.out.println(pname);
    }

    /*
        如何操作运行时类中指定的方法 -- 需要掌握
     */

    @Test
    public void testMethod() throws Exception {

        Class aClass = Person.class;
        //创建运行时类的对象
        Person person = (Person) aClass.newInstance();
        /*
            1.获取指定的方法
            getDeclaredMethod():参数1：指明获取的方法名称。 参数2：指明获取的方法的形参列表
         */
        Method show = aClass.getDeclaredMethod("show", String.class);
        //2.保证当前属性是可以访问的
        show.setAccessible(true);
        /*
            invoke(): 参数1：方法的调用者  参数2：给方法形参赋值的实参
            invoke()返回值即为对应类中调用的方法的返回值
         */
        Object china = show.invoke(person, "China");
        System.out.println(china);

        System.out.println("*****************************");
        //调用静态方法
        Method showDesc = aClass.getDeclaredMethod("showDesc");

        showDesc.setAccessible(true);

        showDesc.invoke(Person.class);
//        showDesc.invoke(null);
    }


}
