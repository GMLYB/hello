package November;

import org.junit.Test;
/*

如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
只有给定的树是单值二叉树时，才返回 true；否则返回 false。

示例 1：
输入：[1,1,1,1,1,null,1]
输出：true

示例 2：
输入：[2,2,2,5,2]
输出：false
 

提示：
    给定树的节点数范围是 [1, 100]。
    每个节点的值都是整数，范围为 [0, 99] 。


 */
public class LeetCode_965 {
    boolean res = true;
    public boolean isUnivalTree(TreeNode root) {
        return Result(root, root.val);
    }

    public boolean Result(TreeNode treeNode, int num) {
        System.out.println("num=" + num);
        System.out.println("val=" + treeNode.val);
        if (treeNode.val != num) {
            res = false;
        }
        if (treeNode.left != null) {
            Result(treeNode.left, num);
        }
        if (treeNode.right != null) {
            Result(treeNode.right, num);
        }

        return res;
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(2);
        treeNode.left = new TreeNode(2);
        System.out.println(isUnivalTree(treeNode));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}