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
    public TreeNode createBinaryTree(int[][] nodes) {
        Set<Integer> children = new HashSet<>();
        Map<Integer, TreeNode> map = new HashMap<>();

        for(int node[] : nodes) {
            // T.C. - O(n) and S.C. -  O(n)
            int parent = node[0], child = node[1], isLeft = node[2];
            children.add(child);

            TreeNode parentNode = map.computeIfAbsent(parent, TreeNode::new);
            TreeNode childNode = map.computeIfAbsent(child, TreeNode::new);

            if(isLeft==1)
                parentNode.left = childNode;
            else parentNode.right = childNode;

        }

        for(int[] node : nodes) {
            if(!children.contains(node[0]))
                return map.get(node[0]);
        }

        return null;
    }
}
