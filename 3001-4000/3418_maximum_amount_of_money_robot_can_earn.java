/****************************Recursive DP Solution ******************************/
class Solution {
    int m, n;
    Integer dp[][][];
    public int maximumAmount(int[][] coins) {
        this.m = coins.length;
        this.n = coins[0].length;

        dp = new Integer[m][n][3]; // 0,1,2

        return helper(coins, 0, 0, 2);
    }

    int helper(int[][] coins, int i, int j, int skips) {
        // base case
        if(i>=m || j>=n)
            return Integer.MIN_VALUE; // invalid path

        if(dp[i][j][skips]!=null)
            return dp[i][j][skips];
        // last cell
        if(i==m-1 && j==n-1) {
            if(coins[i][j] < 0 && skips>0)
                return 0;
            return coins[i][j];
        }

        // right
        int right = helper(coins, i, j+1, skips);
        
        // down
        int down = helper(coins, i+1, j, skips);

        // skip
        int skip = Integer.MIN_VALUE;

        if(coins[i][j] < 0 && skips>0) {
            int downskip = helper(coins, i+1, j, skips-1);
            int rightskip = helper(coins, i, j+1, skips-1);
            skip = Math.max(downskip, rightskip);
        }

        int maxcoins = Math.max(Math.max(down, right)+coins[i][j],
        skip);

        return dp[i][j][skips] = maxcoins;
    }
}

/***********************Iterative DP Solution****************************** */
class Solution {
    int m, n;
    int dp[][][];
    public int maximumAmount(int[][] coins) {
        this.m = coins.length;
        this.n = coins[0].length;

        dp = new int[m+1][n+1][3]; // 0,1,2
        // initialize
        for(int i=0; i<=m; i++) {
            dp[i][n][0] = Integer.MIN_VALUE;
            dp[i][n][1] = Integer.MIN_VALUE;
            dp[i][n][2] = Integer.MIN_VALUE;
        }
        for(int j=0; j<=n; j++) {
            dp[m][j][0] = Integer.MIN_VALUE;
            dp[m][j][1] = Integer.MIN_VALUE;
            dp[m][j][2] = Integer.MIN_VALUE;
        }

        // dp[i][j][k] = from cell[i,j] to last cell , k skips = max coins
        // dp[m-1][n-1][k] = base case
        // dp[0][0][2] = ans

        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                for(int k=0; k<3; k++) {
                    // base case
                    if(i==m-1 && j==n-1) {
                        if(coins[i][j] < 0 && k>0) 
                            dp[i][j][k] = 0;
                        else dp[i][j][k] = coins[i][j];
                    }
                    else {
                        int down = dp[i+1][j][k];
                        int right = dp[i][j+1][k];
                        int skip = Integer.MIN_VALUE;
                        if(coins[i][j] < 0 && k>0) {
                            int downskip = dp[i+1][j][k-1];
                            int rightskip = dp[i][j+1][k-1];
                            skip = Math.max(downskip, rightskip);
                        }
                        dp[i][j][k] = Math.max(Math.max(down, right)+coins[i][j], skip);
                    }
                }
            }
        }

        return dp[0][0][2];
    }
}