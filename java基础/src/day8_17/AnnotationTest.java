package day8_17;

import java.util.ArrayList;
import java.util.Arrays;

public class AnnotationTest {
    public static void main(String[] args) {
        @SuppressWarnings("")
        int num = 10;
    }

}

@MyAnnotation("hi")
class Person{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人走路");
    }

    public void eat(){
        System.out.println("人吃饭");
    }
}

interface Sing{
    void show();
}

//jdk1.8之前
//@MyAnnotations({@MyAnnotation(value = "nihao"),@MyAnnotation(value = "ni")})
//jdk1.8之后
@MyAnnotation(value = "nihao")
@MyAnnotation(value = "ni")
class Student extends Person implements Sing{

    @Override
    public void walk() {
        System.out.println("学生吃饭");
    }

    @Override
    public void show() {
        System.out.println("学生唱歌");
    }

}

class Generic<@MyAnnotation T>{
    ArrayList<@MyAnnotation String> list = new ArrayList<>();
    int num = (@MyAnnotation int)10L;
}