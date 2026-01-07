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
    long max;
    int total;
    int mod = 1_000_000_007;
    public int maxProduct(TreeNode root) {
        // O(n) + O(n) = O(n)
        // O(n)
        total = getSum(root);
        helper(root);
        return (int)(max%mod);
    }

    int getSum(TreeNode root) {
        if(root==null)
            return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    int helper(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftsum = helper(root.left);
        int rightsum = helper(root.right);

        max = Math.max(max, (long)leftsum*(total-leftsum));
        max = Math.max(max, (long)rightsum*(total-rightsum));

        return root.val + leftsum + rightsum;
    }
}



