package November;

import java.util.Stack;
/*

给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
请你返回该链表所表示数字的 十进制值 。

示例 1：
输入：head = [1,0,1]
输出：5
解释：二进制数 (101) 转化为十进制数 (5)

示例 2：
输入：head = [0]
输出：0

示例 3：
输入：head = [1]
输出：1

示例 4：
输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
输出：18880

示例 5：
输入：head = [0,0]
输出：0
 

提示：
    链表不为空。
    链表的结点总数不超过 30。
    每个结点的值不是 0 就是 1。


 */
public class LeetCode_1290 {

    public int getDecimalValue(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        int len = 0;
        while (node.next != null) {
            len++;
            node = node.next;
        }

        int sum = 0;
        while (head != null) {
            if (head.val == 1) {
                sum += Math.pow(2, len - 1);
            }
            len--;
            head = head.next;
        }
        return sum;
    }

    public int getDecimalValue1(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        Stack<Integer> nums = new Stack<>();
        while (node.next != null) {
            nums.push(node.next.val);
            node = node.next;
        }
        int i = 0;
        int sum = 0;
        while (!nums.empty()) {
            int tmp = nums.pop();
            if (tmp == 1) {
                sum += Math.pow(2, i);
            }
            i++;
        }
        return sum;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
