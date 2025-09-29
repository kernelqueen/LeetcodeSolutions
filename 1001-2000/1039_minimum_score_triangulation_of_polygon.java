class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int dp[][] = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i],-1);
        }
        return helper(values, 0, n-1, dp);
    }

    int helper(int[] values, int i, int j, int dp[][]) {
        if(i+1==j)
            return 0;

            if(dp[i][j]!=-1) {
                return dp[i][j];
            }
    

    int ans = Integer.MAX_VALUE;

    for(int k=i+1; k<j; k++) {
        ans = Math.min(ans, values[i]*values[j]*values[k] + helper(values, i, k, dp) + helper(values, k, j, dp));
    }
    dp[i][j] = ans;

    return ans;
    }
}

/* Recursive solution - TLE */
class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        return helper(values, 0, n-1);
    }

    int helper(int[] values, int i, int j) {
        if(i+1==j)
            return 0;
    

    int ans = Integer.MAX_VALUE;

    for(int k=i+1; k<j; k++) {
        ans = Math.min(ans, values[i]*values[j]*values[k] + helper(values, i, k) + helper(values, k, j));
    }

    return ans;
    }
}