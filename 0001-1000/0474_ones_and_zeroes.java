/* Reursive Approach : Gives TLE*/

class Solution {
    // Time complexity : 2^strs.length * L 
    public int findMaxForm(String[] strs, int m, int n) {
        return helper(m, n, 0, strs);
    }

    int helper(int m, int n, int index, String[] strs) {
        // base condition
        if(index >= strs.length) {
            return 0;
        }

        // zeros, ones
        int[] count = func(strs[index]);
        int zeros = count[0];
        int ones = count[1];

        // 2 options, 
        int take = 0;
        if(zeros<=m && ones<=n) {
            take = 1 + helper(m-zeros, n-ones, index+1, strs);
        }
        int skip = helper(m, n, index+1, strs); // the size of subset

        int ans = Math.max(take, skip);
        return ans;

    }

    int[] func(String s) {
        int zeros = 0, ones = 0;
        for(char ch : s.toCharArray()) {
            if(ch=='0')
                zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}
/* Recursive With Memoization */
class Solution {
    int dp[][][];
    public int findMaxForm(String[] strs, int m, int n) {
        dp = new int[m+1][n+1][strs.length];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                for(int k=0; k<strs.length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return helper(m, n, 0, strs);
    }

    int helper(int m, int n, int index, String[] strs) {
        // base condition
        if(index >= strs.length) {
            return 0;
        }

        if(dp[m][n][index]!=-1) {
            return dp[m][n][index];
        }

        // zeros, ones
        int[] count = func(strs[index]);
        int zeros = count[0];
        int ones = count[1];

        // 2 options, 
        int take = 0;
        if(zeros<=m && ones<=n) {
            take = 1 + helper(m-zeros, n-ones, index+1, strs);
        }
        int skip = helper(m, n, index+1, strs); // the size of subset

        int ans = Math.max(take, skip);
        dp[m][n][index] = ans;
        return ans;

    }

    int[] func(String s) {
        int zeros = 0, ones = 0;
        for(char ch : s.toCharArray()) {
            if(ch=='0')
                zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}


*/

/*Iterative Solution Approach */

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int[] count = countZerosOnes(s);
            int zeros = count[0];
            int ones = count[1];

            // Traverse backwards to avoid reusing the same string multiple times
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeros][j - ones]);
                }
            }
        }

        return dp[m][n];
    }

    private int[] countZerosOnes(String s) {
        int zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}