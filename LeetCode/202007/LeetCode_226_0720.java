package July;

/*
翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1
备注:
这个问题是受到 Max Howell 的 原问题 启发的 ：
谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。

 */

public class LeetCode_226 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        //左右都不为空，就左右调换
        if (root.left != null && root.right!= null){
            TreeNode tmp1 = root.left;
            root.left = root.right;
            root.right = tmp1;
            invertTree(root.left);
            invertTree(root.right);
        //左空 右不空，就把 左 赋值 右，右为空
        }else if (root.left == null && root.right!= null){
            root.left = root.right;
            root.right = null;
            invertTree(root.left);
        //右空 左不空，就把 右 赋值 左，左为空
        }else if (root.left != null && root.right == null){
            root.right = root.left;
            root.left = null;
            invertTree(root.right);
        }
        return root;
    }


}
