/*
 * LeetCode 3532. Path Existence Queries in a Graph I
 * https://leetcode.com/problems/path-existence-queries-in-a-graph-i/
 *
 * nums is sorted, so connected components are contiguous index runs:
 * node i reaches i+1 iff |nums[i] - nums[i+1]| <= maxDiff.
 */

// Approach 1: Union-Find (DSU) with path compression.
class Solution {
    int parent[];
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        parent = new int[n];
        for(int i=0; i<n ;i++) {
            parent[i]=i; }                        // each node is its own root initially
        for(int i=0; i<n-1; i++) {
            int num1 = nums[i], num2 = nums[i+1];  // only consecutive pairs can be edges

            if(Math.abs(num1 - num2) <= maxDiff) {
                union(i, i+1);                     // within gap -> merge the two nodes
            }
        }
        boolean ans[] = new boolean[queries.length];
        int index = 0;
        for(int q[] : queries) {
            int x = q[0];
            int y = q[1];

            ans[index++] = (find(x)==find(y));     // same root -> path exists
        }
        return ans;
    }
    private int find(int x) {
        if(parent[x]!=x)
            parent[x] = find(parent[x]);           // path compression: point straight to root
        return parent[x];
    }
    private void union(int x, int y) {
        parent[find(x)] = find(y);                 // hang x's root under y's root
    }
}
// Time: O((n + q) * alpha(n))   Space: O(n)

// ----------------------------------------------------------------------

// Approach 2: Linear component labeling in one pass, then compare labels.
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int comp[] = new int[n];
        // comp[0] = 0                             // first node anchors component 0

        for(int i=1; i<n; i++) {
            // O(n)
            int diff = Math.abs(nums[i]-nums[i-1]);
            if(diff <= maxDiff)
                comp[i] = comp[i-1];               // connected -> inherit previous label
            else comp[i] = i;                      // gap too big -> start a new component
        }

        boolean ans[] = new boolean[queries.length];
        for(int i=0; i<queries.length; i++) {
            int node1 = queries[i][0], node2 = queries[i][1];
            if(comp[node1]==comp[node2])
                ans[i]=true;                       // same label -> same component -> reachable
        }
        return ans;
    }
}
// Time: O(n + q)   Space: O(n)