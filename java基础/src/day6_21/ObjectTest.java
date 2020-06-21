package day6_21;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之三：对象流
 *
 * 1. ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把java中的对象写入到数据中，也能把对象从数据源中还原回来
 *
 * 序列化：用 ObjectOutputStream 类 保存 基本类型或对象的机制
 * 反序列化：用 ObjectInputStream 类 读取 基本类型数据或对象的机制
 *
 *
 *
 */

public class ObjectTest {

    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */

    @Test
    public void testObjectOutputStream(){

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("src\\day6_21\\object.lyb")));

            oos.writeObject(new String("我秋梨膏，太难了！"));
            oos.flush();

            oos.writeObject(new Person("天才小熊猫",23));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    反序列化：将磁盘文件中对象还原为内存的一个java对象
    使用ObjectInputStream实现
     */

    @Test
    public void testObjectInputStream(){

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("src\\day6_21\\object.lyb")));

            Object obj = ois.readObject();

            String str = (String) obj;

            Person p = (Person)ois.readObject();

            System.out.println(str);
            System.out.println(p);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
