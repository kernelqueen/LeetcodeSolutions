/******Recursive with Memoization******* */
class Solution {
    int nums1[];
    int nums2[];
    Integer dp[][];
    public int maxDotProduct(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        dp = new Integer[nums1.length][nums2.length];
        return helper(0,0); 
    }

    int helper(int i, int j) {
        // base case
        if(i>=nums1.length || j>=nums2.length) {
            return Integer.MIN_VALUE;
        }
        if(dp[i][j]!=null) {
            return dp[i][j];
        }
        int skip1 = helper(i+1, j);
        int skip2 = helper(i, j+1);
        int take = nums1[i]*nums2[j] + Math.max(0, helper(i+1, j+1));

        return dp[i][j] = Math.max(Math.max(skip1, skip2), take);
    }

    
}
/**************Iterative Dp******** */
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];

        // initialize base cases
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int take = nums1[i] * nums2[j]
                         + Math.max(0, dp[i + 1][j + 1]);

                int skip1 = dp[i + 1][j];
                int skip2 = dp[i][j + 1];

                dp[i][j] = Math.max(take, Math.max(skip1, skip2));
            }
        }

        return dp[0][0];
    }
}