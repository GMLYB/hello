package January;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*

697. 数组的度
给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

示例 1:

输入: [1, 2, 2, 3, 1]
输出: 2
解释:
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
示例 2:

输入: [1,2,2,3,1,4,2]
输出: 6
注意:

nums.length 在1到50,000区间范围内。
nums[i] 是一个在0到49,999范围内的整数。

 */
public class LeetCode_697 {
    public int findShortestSubArray(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        int max = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if (entry.getValue() > max) {
                list.clear();
                max = entry.getValue();
                list.add(entry.getKey());
            } else if (entry.getValue() == max) {
                list.add(entry.getKey());
            }

        }

        if (max == 1) {
            return 1;
        }

        int len = 99999;

        for (Integer num : list) {
            int tmp = 0;
            //头找
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == num) {
                    tmp = i;
                    break;
                }
            }
            //尾找
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == num) {
                    tmp = i - tmp + 1;
                    break;
                }
            }
            len = Math.min(len, tmp);
        }

        return len;

    }
}
