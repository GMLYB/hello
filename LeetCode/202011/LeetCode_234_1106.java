package November;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/*

请判断一个链表是否为回文链表。

示例 1:
输入: 1->2
输出: false
示例 2:
输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 */

public class LeetCode_234 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        LinkedList<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        while (node.next != null) {
            queue.add(node.next.val);
            stack.add(node.next.val);
            node = node.next;
        }
        while (!stack.empty()) {
            int num1 = queue.pop();
//            System.out.println(num1);
            int num2 = stack.pop();
//            System.out.println(num2);
            if (num1 != num2){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(node));
    }
}
