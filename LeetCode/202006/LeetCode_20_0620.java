package day6_20;

import java.util.HashMap;
import java.util.Stack;

    /*
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。

        示例 1:
        输入: "()"
        输出: true

        示例 2:
        输入: "()[]{}"
        输出: true

        示例 3:
        输入: "(]"
        输出: false

        示例 4:
        输入: "([)]"
        输出: false

        示例 5:
        输入: "{[]}"
        输出: true

    */

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
