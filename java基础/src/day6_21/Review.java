package day6_21;

import org.junit.Test;

import java.io.*;

public class Review {

    /*
    创建文件，写入信息
     */
    @Test
    public void testWriterReview() {
        FileWriter fw = null;
        try {
            File file = new File("src\\day6_21\\hello.txt");

            fw = new FileWriter(file);

            fw.write("hello I am a 中国人!\n");
            fw.write("hello I love!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     /*
        读hello.txt，输出到屏幕上
         */

    @Test
    public void testReader() {
        FileReader fr = null;
        try {
            File file = new File("src\\day6_21\\hello.txt");

            fr = new FileReader(file);

            char[] arr = new char[1024];
            int len;
            while ((len = fr.read(arr)) != -1){
                String s = new String(arr,0,len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
