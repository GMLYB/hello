package July;

/*
给定一个二叉树，检查它是否是镜像对称的。
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3

 */

public class LeetCode_101 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public boolean isSymmetric(TreeNode root) {
        //根节点为空
        if (root == null){
            return true;
        }

        return pd(root.left,root.right);
    }


    public boolean pd(TreeNode left, TreeNode right){

        //左右都为空
        if (left == null && right == null){
            return true;
        }
//        存在一个为空的情况
        if (right == null || left == null){
            return false;
        }
//        值不相同
        if (left.val != right.val){
            return false;
        }
//        左边的左边和右边的右边比   左边的右边和右边的左边比
         return pd(left.left,right.right)&&pd(left.right,right.left);
    }
}
