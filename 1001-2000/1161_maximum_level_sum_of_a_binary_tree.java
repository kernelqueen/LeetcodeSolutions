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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxsum = Integer.MIN_VALUE;
        int maxlevel = -1, level = 1;
        // O(n)
        // h, 2^h, O(2^h)
        while(!q.isEmpty()) {

            int levelsize = q.size();
            int sum = 0;
            for(int i=0; i<levelsize; i++) {
                TreeNode curr = q.remove();
                sum += curr.val;
                if(curr.left!=null) {
                    q.add(curr.left);
                }
                if(curr.right!=null) {
                    q.add(curr.right);
                }
            }
            if(sum > maxsum) {
                maxsum = sum;
                maxlevel = level;
            }
            level++;

        }

        return maxlevel;
    }
}