package day6_20;

import java.util.HashMap;
import java.util.Stack;

public class LeetCode_20 {
    public boolean isValid(String s) {
        if(s.length() == 0){
            return true;
        }
        if(s.length() % 2 != 0){
            return false;
        }

        HashMap<Character,Character> arr = new HashMap<>();
        arr.put('(',')');
        arr.put('{','}');
        arr.put('[',']');
        if(s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}' ){
            return false;
        }

        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if(stack.empty()){
                stack.push(s.charAt(i));
            }else {
                if(arr.containsKey(stack.peek()) == false){
                    return false;
                }
                if (arr.get(stack.peek()) != s.charAt(i)){
                    stack.push(s.charAt(i));
                }else {
                    stack.pop();
                }
            }
        }
        if(stack.empty()){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        LeetCode_20 code20 = new LeetCode_20();

        String s = "{[]}";
        System.out.println(code20.isValid(s));
    }

}
