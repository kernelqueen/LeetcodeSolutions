class Solution {
    int mod = 1_000_000_007;
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // DP max dp min 
        // O(m*n)
        long dpMax[][] = new long[m][n];
        long dpMin[][] = new long[m][n];

        dpMax[0][0] = grid[0][0];
        dpMin[0][0] = grid[0][0];

        // first row
        for(int j=1; j<n; j++) {
            dpMax[0][j] = dpMax[0][j-1]*grid[0][j];
            dpMin[0][j] = dpMin[0][j-1]*grid[0][j];
        }

        // First col
        for(int i=1; i<m; i++) {
            dpMax[i][0] = dpMax[i-1][0]*grid[i][0];
            dpMin[i][0] = dpMin[i-1][0]*grid[i][0];
        }
        // O(m*n)
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                int val = grid[i][j];
                long topMax = dpMax[i-1][j]*val;
                long topMin = dpMin[i-1][j]*val;
                long leftMin = dpMin[i][j-1]*val;
                long leftMax = dpMax[i][j-1]*val;

                dpMax[i][j] = Math.max(Math.max(topMax, topMin),
                            Math.max(leftMin, leftMax));
                dpMin[i][j] = Math.min(Math.min(topMax, topMin),
                            Math.min(leftMin, leftMax));

            }
        }

        long ans = dpMax[m-1][n-1];

        return ans<0 ? -1 : (int)(ans%mod);

    }
}