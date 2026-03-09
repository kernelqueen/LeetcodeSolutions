class Solution {
    int mod = 1_000_000_007;
    int dp[][][][];
    public int numberOfStableArrays(int zeros, int ones, int limit) {
        dp = new int[2][zeros+1][ones+1][limit+1];
        for(int[][][] a : dp) {
            for(int[][] b : a) {
                for(int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        return (helper(zeros-1, ones, 0, 1, limit) + helper(zeros, ones-1, 1
        , 1 , limit))%mod;
    }

    int helper(int zeros, int ones, int lastbit, int streak, int limit) {
        // base condition
        if(streak > limit)
            return 0; // invalid
        if(zeros==0 && ones==0)
            return 1;
        if(dp[lastbit][zeros][ones][streak]!=-1)
            return dp[lastbit][zeros][ones][streak];
        int ways = 0;
        if(lastbit ==0 ) {
            if(zeros > 0)
                ways = (ways + helper(zeros-1, ones, 0, streak + 1, limit))%mod;
            if(ones > 0)
                ways = (ways + helper(zeros, ones-1, 1, 1, limit))%mod;
        }
        else {
            if(zeros > 0)
                ways = (ways + helper(zeros-1, ones, 0, 1, limit))%mod;
            if(ones > 0)
                ways = (ways + helper(zeros, ones-1, 1, streak+1, limit))%mod;
        }

        return dp[lastbit][zeros][ones][streak] = ways;
    }
}