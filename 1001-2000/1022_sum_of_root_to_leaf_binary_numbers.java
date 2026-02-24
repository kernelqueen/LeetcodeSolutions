/* Approach 1: Using String */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return helper(root, "");
    }

    private int helper(TreeNode root, String num) {
        if(root==null)
            return 0;
        // leaf node
        if(root.left==null && root.right==null)
            return Integer.parseInt(num + root.val, 2); // 101 - 5
        return helper(root.left, num + root.val) + 
        helper(root.right, num + root.val);
    }
}



/*Approach 2: Using Direct sum */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return helper(root, 0);
    }
    /*
        number - 101
        1
        1*2 + 0
        2*2 + 1
    */
    private int helper(TreeNode root, int num) {
        if(root==null)
            return 0;
        // leaf node
        if(root.left==null && root.right==null)
            return num*2 + root.val;
        return helper(root.left, num*2 + root.val) + 
        helper(root.right, num*2 + root.val);
    }
}
