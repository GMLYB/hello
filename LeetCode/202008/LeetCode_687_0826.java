package August;
/*
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:
输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:
2

示例 2:
输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:
2

注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */

public class LeetCode_687 {
    int maxnum = 0;
    public int longestUnivaluePath(TreeNode root) {
        int num = maxPath(root,root.val);
        System.out.println("num = " + num);
        return Math.max(maxnum,maxPath(root,root.val))-1;
    }

    // error
    public int maxPath(TreeNode node,int num){
        if (node == null ||  node.val == -1){
            return 0;
        }
        int sum = 0;
        if (node.val == num){
            sum = 1;
        }
        sum += maxPath(node.left,node.val) + maxPath(node.right,node.val);
        maxnum = Math.max(sum,maxnum);
        // System.out.println("maxnum = " + maxnum);
        node.val = -1;
        return sum;

    }

    public static void main(String[] args) {
        LeetCode_687 code  = new LeetCode_687();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node4;
        node1.left = node2;
        node1.right = node3;
        node4.right = node5;

        System.out.println(code.longestUnivaluePath(root));
    }

    //true
    /*
       int maxnum = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null){
            return 0;
        }
        maxPath(root,root.val);
        return maxnum;
    }

    public int maxPath(TreeNode r,int val){
        if(r == null) return 0;
        int left = maxPath(r.left, r.val);
        int right = maxPath(r.right, r.val);
        maxnum = Math.max(maxnum, left+right); // 路径长度为节点数减1所以此处不加1
        if(r.val == val){
             return Math.max(left, right) + 1;
        }
        return 0;
    }

     */

    //false
    /*
    int maxnum = 0;
    public int longestUnivaluePath(TreeNode root) {
        maxPath(root,root.val);
        return maxnum;
    }

    public int maxPath(TreeNode node,int num){
        if (node == null){
            return 0;
        }
        int l = maxPath(node.left,node.val);
        int r = maxPath(node.right,node.val);
        int sum = l + r;
        maxnum = Math.max(sum,maxnum);
       // System.out.println("maxnum = " + maxnum);
        if (node.val == num){
            return Math.max(r,l) + 1;
        }
        return 0;

    }

     */

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}