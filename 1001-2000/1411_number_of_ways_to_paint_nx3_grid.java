/* Recursive Solution With Memoization */
class Solution {
    long MOD = 1_000_000_007L;
    Long dp[][];
    public int numOfWays(int n) {
        dp = new Long[n][2];
        long result =(6L*ways(1, n, 0))%MOD + (6L*ways(1, n , 1))%MOD;
        return (int)(result%MOD);
    }

    long ways(int row, int n, int type) {
        // base case
        if(row==n) {
            return 1L;
        }
        if(dp[row][type]!=null) {
            return dp[row][type];
        }
        long ans = 0;
        if(type==0) {
            ans = (2L*ways(row+1, n, type))%MOD + (2L*ways(row+1, n, 1))%MOD;
        }
        else if(type==1) {
            ans =  (2L*ways(row+1, n, 0))%MOD + (3L*ways(row+1, n, type))%MOD;
        }
        dp[row][type] = ans%MOD;
        return dp[row][type];
    }
}

/* Iterative Solution */
class Solution {
    long MOD = 1_000_000_007L;
    long dp[][];
    public int numOfWays(int n) {
        dp = new long[n][2];
        dp[0][0] = 6;
        dp[0][1] = 6;
        for(int i=1; i<n; i++) {
            dp[i][0] = (2*dp[i-1][0] + 2*dp[i-1][1])%MOD;
            dp[i][1] = (2*dp[i-1][0] + 3*dp[i-1][1])%MOD;
        }
        return (int)((dp[n-1][0] + dp[n-1][1])%MOD);
        
    }

}