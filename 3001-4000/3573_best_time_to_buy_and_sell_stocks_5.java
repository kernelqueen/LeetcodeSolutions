/******************Recursive Apporach: TLE ******************/
class Solution {
    public long maximumProfit(int[] prices, int k) {
        return helper(0, k, 0, prices);
    }

    long helper(int i, int k, int state, int prices[]) {

        if(prices.length==i) {
            return state==0 ? 0 : Integer.MIN_VALUE;
        }

        long skip = helper(i+1, k, state, prices);

        long take = Integer.MIN_VALUE;

        // option 1:  
        if(state==0) {
            long buy = -prices[i] + helper(i+1, k, 1, prices);
            long sell = prices[i] + helper(i+1, k, 2, prices);
            take = Math.max(take, Math.max(buy, sell));
        }
        else if(state==1 && k>0) {
            take = prices[i] + helper(i+1, k-1, 0, prices);
        } else if(state==2 && k>0) {
            take = -prices[i] + helper(i+1, k-1, 0, prices);
        }

        return Math.max(skip, take);
    }
}

/*Recursive approach with Memoization */
class Solution {

    // dp[i][k][state]
    long[][][] dp;

    long helper(int i, int k, int state, int[] prices) {

        if (i == prices.length) {
            return state == 0 ? 0 : Integer.MIN_VALUE;
        }

        if (dp[i][k][state] != Integer.MIN_VALUE)
            return dp[i][k][state];

        long skip = helper(i + 1, k, state, prices);

        long take = Integer.MIN_VALUE;

        if (state == 0) {
            long sell = prices[i] + helper(i + 1, k, 2, prices);
            long buy  = -prices[i] + helper(i + 1, k, 1, prices);
            take = Math.max(sell, buy);
        }
        else if (state == 1 && k > 0) {
            take = prices[i] + helper(i + 1, k - 1, 0, prices);
        }
        else if (state == 2 && k > 0) {
            take = -prices[i] + helper(i + 1, k - 1, 0, prices);
        }

        return dp[i][k][state] = Math.max(take, skip);
    }

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        dp = new long[n][k + 1][3];

        for (int i = 0; i < n; i++)
            for (int j = 0; j <= k; j++)
                for (int s = 0; s < 3; s++)
                    dp[i][j][s] = Integer.MIN_VALUE;

        return helper(0, k, 0, prices);
    }
}