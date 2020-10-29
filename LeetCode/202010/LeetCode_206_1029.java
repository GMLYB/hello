package October;

import java.util.Stack;
/*
反转一个单链表。

示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL

进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 */
public class LeetCode_206 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode node = new ListNode(-1);
        ListNode node2 = node;

        node.next = head;
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.empty()){
            System.out.println(stack.peek());
            node.next.val = stack.pop();
            node = node.next;
        }

        return node2.next;
    }
}
