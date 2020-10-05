package October;

import java.util.ArrayList;
import java.util.List;

/*
写一个程序，输出从 1 到 n 数字的字符串表示。
1. 如果 n 是3的倍数，输出“Fizz”；
2. 如果 n 是5的倍数，输出“Buzz”；
3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
示例：
n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]

 */
public class LeetCode_412 {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 15 == 0) {
                sb.append( "FizzBuzz");
            }else if (i % 5 == 0){
                sb.append("Buzz");
            }else if (i % 3 == 0){
                sb.append("Fizz");
            }else {
                sb.append(i);
            }
            list.add(sb.toString());
        }

        return list;
    }
}
