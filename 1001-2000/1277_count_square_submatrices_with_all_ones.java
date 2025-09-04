class Solution {
    public int countSquares(int[][] matrix) {
        
        int m = matrix.length;
        int n = matrix[0].length;

        int dp[][] = new int[m][n];

        int ans = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                ans += helper(i, j, matrix, dp);
            }
        }
        return ans;
    }

    int helper(int i, int j, int matrix[][], int dp[][]) {
        // base cases

        if(i<0 || j<0) {
            return 0;
        }

        if(matrix[i][j]==0) {
            return 0;
        }

        if(dp[i][j]!=0) {
            return dp[i][j];
        }

        // if the cell value is 1

        int left = helper(i, j-1, matrix, dp);
        int up = helper(i-1, j, matrix, dp);
        int digonal = helper(i-1, j-1, matrix, dp);

        return dp[i][j] = 1 + Math.min(left, Math.min(up, digonal));
    }
}
