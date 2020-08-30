package August;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

自除数 是指可以被它包含的每一位数除尽的数。

例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。

还有，自除数不允许包含 0 。

给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。

示例 1：

输入：
上边界left = 1, 下边界right = 22
输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]

注意：
每个输入参数的边界满足 1 <= left <= right <= 10000。

 */

public class LeetCode_728 {
    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> list = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (String.valueOf(i).indexOf('0') == -1){
                String tmp = String.valueOf(i);
                boolean isnum = true;
                for (int j = 0; j < tmp.length(); j++) {
                    if (i % (tmp.charAt(j) - '0') != 0){
//                        System.out.println("i:"+ i + ":" + i % (int)tmp.charAt(j));
                        isnum = false;
                        break;
                    }
                }
                if (isnum){
                    list.add(i);
                }
            }
        }

        return list;

    }

    public static void main(String[] args) {


        LeetCode_728 code = new LeetCode_728();

        List<Integer> list = code.selfDividingNumbers(1, 22);
        list.forEach(System.out::println);

    }
}

