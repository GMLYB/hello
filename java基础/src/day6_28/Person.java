package day6_28;

import java.util.Objects;

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
//    //手动实现equals比较
//    //重写原则：比较两个对象的实体内容是否相同(即name和age)
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj){
//            return true;
//        }
//        if (obj instanceof Person){
//            Person person = (Person) obj;
//            if (this.age == person.age && this.name.equals(person.name)){
//                return true;
//            }
//            return false;
//        }
//
//        return false;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //自动生成
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
