import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import pojo.Person;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    /**
     * javaBean 和 json 的互转
     */
    @Test
    public void test1(){
        Person person = new Person(1, "年少，白了头");
        System.out.println("person : " + person);
        //创建Gson对象实例
        Gson gson = new Gson();
        //调用toJson()方法把java对象转换为json字符串
        String personstring = gson.toJson(person);
        System.out.println("personstring : " + personstring);
        //调用fromJson()方法转换为java对象
        Person person1 = gson.fromJson(personstring, Person.class);
        System.out.println("person1 : "+person1);

    }

    /**
     * List 和 json 的互转
     */
    @Test
    public void test2(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"男人"));
        list.add(new Person(2,"至死是少年"));
        System.out.println("list : " + list);
        Gson gson = new Gson();
        //把list转换成 json 字符串
        String liststring = gson.toJson(list);
        System.out.println("liststring : " + liststring);
        //把 json字符串 转换为list
        //通过匿名内部类方式创建 TypeToken 类，并调用getType()方法得到Type对象
        Type type = new TypeToken<List<Person>>(){}.getType();
        //将 json字符串和type传入fromJson()方法中
        List<Person> list1 = gson.fromJson(liststring, type);
        System.out.println("list1 : " + list1);
    }


    /**
     * Map 和 json 的互转
     */
    @Test
    public void test3(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"男人");
        map.put(2,"至死是少年");
        System.out.println("list : " + map);
        Gson gson = new Gson();
        //把list转换成 json 字符串
        String mapstring = gson.toJson(map);
        System.out.println("mapstring : " + mapstring);
        //把 json字符串 转换为list
        //通过匿名内部类方式创建 TypeToken 类，并调用getType()方法得到Type对象
        Type type = new TypeToken<Map<Integer,String>>(){}.getType();
        //将 json字符串和type传入fromJson()方法中
        Map<Integer,String> map1 = gson.fromJson(mapstring, type);
        System.out.println("map1 : " + map1);
    }
}
