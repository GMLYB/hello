package August;

/*

给定一棵二叉树，你需要计算它的直径长度。
一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树
          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。

 */

public class LeetCode_543 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int max = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max - 1;//路径为：根节点数-1
    }


    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);//左边的节点数
        int R = depth(node.right);//右边的节点数

        max = Math.max(max, L + R + 1);//求出最大的节点数（左节点数+有节点数 + 根节点）

        return Math.max(L, R) + 1;//返回该节点为根节点时的最大深度
    }
}
