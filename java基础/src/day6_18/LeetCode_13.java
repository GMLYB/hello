package day6_18;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_13 {

    public int romanToInt(String s) {
        Map<Character,Integer> num = new HashMap<>();
        int sum = 0;
        num.put('M',1000);
        num.put('D',500);
        num.put('C',100);
        num.put('L',50);
        num.put('X',10);
        num.put('V',5);
        num.put('I',1);
        for (int i = 1; i < s.length(); i++) {
//            System.out.println("字符："+s.charAt(i-1));
            sum += num.get(s.charAt(i-1));
//            System.out.println("外和：" + sum);
            if(num.get(s.charAt(i-1)) < num.get(s.charAt(i))){
                sum -= num.get(s.charAt(i-1)) * 2;
//                System.out.println("内和：" + sum);
            }
        }
        return sum+num.get(s.charAt(s.length()-1));
    }

    public static void main(String[] args) {

        LeetCode_13 roman = new LeetCode_13();
        System.out.println(roman.romanToInt("XXVII"));

    }
}
