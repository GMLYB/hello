package July;

/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
示例：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

 */


public class LeetCode_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }

        ListNode prehead = new ListNode(0);
        ListNode node = prehead;

        //两个都不为空的时候
        while (l1 != null && l2 != null){
            if (l1.val>= l2.val){
                node.next = l2;
                l2 = l2.next;
            }else{
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        //l1不为空
        while (l1 != null){
            node.next = l1;
            l1 = l1.next;
        }
        //l2不为空
        while (l2 != null){
            node.next = l2;
            l2 = l2.next;
        }

        return prehead.next;

    }

}



class ListNode {
    int val;
    ListNode next;
     ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
