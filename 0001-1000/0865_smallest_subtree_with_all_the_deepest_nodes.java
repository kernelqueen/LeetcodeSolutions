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
    TreeNode ans;
    int maxdepth;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // O(n)
        // O(n) = skewed 
        ans = null;
        maxdepth = -1;
        helper(root, 0);
        return ans;
    }

    int helper(TreeNode root, int level) {
        if(root==null) {
            return 0;
        }

        int left = helper(root.left, level+1);
        int right = helper(root.right, level+1);

        if(left==right && (left+level)>=maxdepth) {
            ans = root;
            maxdepth = left + level;
            System.out.println(ans.val);
        }

        return 1 + Math.max(left, right);
    }
}