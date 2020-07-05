package July;

import java.util.HashSet;
import java.util.Set;

/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
输入: 1->1->2
输出: 1->2

示例 2:
输入: 1->1->2->3->3
输出: 1->2->3
 */
public class LeetCode_83 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode cur = head;
        while (cur.next != null){
            if (set.contains(cur.next.val)){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
                set.add(cur.val);
            }
        }

        return head;
    }
}
