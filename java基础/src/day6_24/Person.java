package day6_24;

public class Person {

    private String name;
    private int age;
    private boolean sex;
    public int id;


    public Person() {
    }

    public Person(String name, int age, boolean sex, int id) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
    }

    private String show(String nation){
        System.out.println("国籍为："+nation);
        return nation;
    }
    private static void showDesc(){
        System.out.println("我是一个(*╹▽╹*)的人");
    }



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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", id=" + id +
                '}';
    }
}
