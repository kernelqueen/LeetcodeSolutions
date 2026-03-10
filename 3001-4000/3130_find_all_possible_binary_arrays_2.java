
class Solution {
    public int numberOfStableArrays(int zeros, int ones, int limit) {
        int dp[][][] = new int[zeros+1][ones+1][2];
        // O(n^2)
        // O(n^2)
        int mod = 1_000_000_007;
        // dp array initialize
        for(int i=1; i<=zeros; i++)
            dp[i][0][0] = (i<=limit) ? 1 : 0;
        for(int j=1; j<=ones; j++)
            dp[0][j][1] = (j<=limit) ? 1 : 0;
        
        for(int i=1; i<=zeros; i++) {
            for(int j=1; j<=ones; j++) {
                // last bit = 0
                dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1])%mod;
                // limit
                if(i > limit)
                    dp[i][j][0] = (dp[i][j][0] - dp[i-limit-1][j][1] + mod)%mod;
                // 1 0 0 0 
                // limit = 2

                // last bit = 1
                dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1])%mod;
                if(j > limit)
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j-limit-1][0] + mod)%mod;
            }
        }
        return (dp[zeros][ones][0] + dp[zeros][ones][1])%mod;
    }
}