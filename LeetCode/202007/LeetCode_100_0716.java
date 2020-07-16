package July;

/*
给定两个二叉树，编写一个函数来检验它们是否相同。
如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:
输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]
输出: true

示例 2:
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]
输出: false

示例 3:
输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]
输出: false

 */

public class LeetCode_100 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
//        都为空，就一样
        if (p == null && q == null){
            return true;
        }
//        其中一个不为空，就不一样
        if (q == null || p == null){
            return false;
        }
        //值不相同，返回 false
        if(p.val != q.val){
            return false;
        }
        //只有左边和右边都为 true的时候，才返回true
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

}
