/*************Recursive with Memoization************** */
class Solution {
    Integer dp[][];
    public int minimumDeleteSum(String s1, String s2) {
        dp = new Integer[s1.length()][s2.length()];
        return helper(s1, s2, 0, 0);
    }

    int helper(String s1, String s2, int i, int j) {
        // base case
        if(i>=s1.length() || j>=s2.length()) {
            return remSum(s1, i) + remSum(s2, j);
        }
        if(dp[i][j]!=null) {
            return dp[i][j];
        }
        int ans = 0;
        if(s1.charAt(i)==s2.charAt(j)) {
            ans = helper(s1, s2, i+1, j+1);
        }
        else {
            ans = Math.min(s1.charAt(i) + helper(s1, s2, i+1, j), 
                            s2.charAt(j) + helper(s1, s2, i, j+1));
        }
        return dp[i][j] = ans;
    }

    int remSum(String str, int ind) {
        int sum = 0;
        for(int i=ind; i<str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum;
    }
}


/*************Iterative DP Solution************** */

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int dp[][] = new int[m+1][n+1];

        // s1 exhausted
        for(int j=n-1; j>=0; j--) {
            dp[m][j] = dp[m][j+1] + s2.charAt(j);
        }

        // s2 exhausted
        for(int i=m-1; i>=0; i--) {
            dp[i][n] = dp[i+1][n] + s1.charAt(i);
        }

        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(s1.charAt(i)==s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min(s1.charAt(i) + dp[i+1][j],
                    s2.charAt(j) + dp[i][j+1]);
                }
            }
        }

        return dp[0][0];


    }
}








