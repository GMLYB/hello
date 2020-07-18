package July;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 */

public class LeetCode_104 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return maxDepth(root.left,root.right);
    }

    public int maxDepth(TreeNode left, TreeNode right){
        int i = 1;
        int j = 1;
        //左节点不为空 就用下一层 加上 1
        if (left != null){
            i = maxDepth(left.left,left.right)+1;
        }
        if (right != null){
            j = maxDepth(right.left,right.right)+1;
        }

        if (i > j){
            return i;
        }

        return j;
    }



}
