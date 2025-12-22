/***************Recursive: Gives TLE********************** */
class Solution {
    public int minDeletionSize(String[] strs) {
        
        return helper(-1, 0, strs);
    }

    int helper(int prev, int curr, String strs[]) {
        int m = strs[0].length(); // columns length
        int n = strs.length;
        // base case
        if(curr==m) {
            return 0;
        }

        int del = Integer.MAX_VALUE, notdel = Integer.MAX_VALUE;
        boolean order = true;

        if(prev!=-1) {
            for(int i=0; i<n; i++) {
                if(strs[i].charAt(curr) < strs[i].charAt(prev)) {
                    order = false;
                    break;
                }
            }
        }

        del = 1 + helper(prev, curr+1, strs);
        if(prev==-1 || order==true) {
            notdel = helper(curr, curr+1, strs);
        }
        return Math.min(del, notdel);
    }
}

/****************Recursive with Memoization********************** */
class Solution {
    int dp[][];
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length();
        dp = new int[m+1][m+1];
        for(int i=0; i<m+1; i++) {
            Arrays.fill(dp[i],-1);
        }
        return helper(-1, 0, strs);
    }

    int helper(int prev, int curr, String strs[]) {
        int m = strs[0].length(); // columns length
        int n = strs.length;
        // base case
        if(curr==m) {
            return 0;
        }

        int del = Integer.MAX_VALUE, notdel = Integer.MAX_VALUE;
        boolean order = true;

        if(prev!=-1) {
            if(dp[prev][curr]!=-1)
                return dp[prev][curr];
            for(int i=0; i<n; i++) {
                if(strs[i].charAt(curr) < strs[i].charAt(prev)) {
                    order = false;
                    break;
                }
            }
        }

        del = 1 + helper(prev, curr+1, strs);
        if(prev==-1 || order==true) {
            notdel = helper(curr, curr+1, strs);
        }
        if(prev!=-1) {
            dp[prev][curr] = Math.min(del, notdel);
        }
        return Math.min(del, notdel);
    }
}
/***************Iterative with Tabulation********************** */
class Solution {
    int m,n;
    int dp[][];
    public int minDeletionSize(String[] strs) {
        m = strs[0].length();
        n = strs.length;
        dp = new int[m+1][m+1];
        for(int i=0; i<m+1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return helper(-1, 0, strs);

    }
    int helper(int prev, int curr, String[] strs) {
        if (curr == m) return 0;

        int ans = Integer.MAX_VALUE;

        boolean order = true;
        if (prev != -1) {
            if(dp[prev][curr]!=-1)
                return dp[prev][curr];
            for (int i = 0; i < n; i++) {
                if (strs[i].charAt(curr) < strs[i].charAt(prev)) {
                    order = false;
                    break;
                }
            }
        }

        // Option 1: delete current column
        int delans = 1 + helper(prev, curr + 1, strs);

        // Option 2: keep current column (only if valid)
        int nodelans = ans;
        if (prev == -1 || order) {
            nodelans = helper(curr, curr + 1, strs);
        }
        if(prev!=-1)
            dp[prev][curr] = Math.min(delans, nodelans);

        return Math.min(delans, nodelans);
    }

}