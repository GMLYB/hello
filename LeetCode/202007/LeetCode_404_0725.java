package July;

/*
计算给定二叉树的所有左叶子之和。

示例：

    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
    
 */

public class LeetCode_404 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null){
            return 0;
        }
        int sum = 0;
        //左节点不为空 ； 左节点的左右节点都为空（即为叶子节点） 就加上它的值
        if (root.left != null && (root.left.left == null && root.left.right == null)){
            sum += root.left.val;
        }

        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }



}
