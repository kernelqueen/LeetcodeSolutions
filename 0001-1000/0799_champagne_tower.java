class Solution {
    public double champagneTower(int poured, int rows, int glass) {
        double dp[][] = new double[101][101];

        dp[0][0] = poured;

        // O(rows*rows)
        for(int i=0; i<=rows; i++) {
            for(int j=0; j<=i; j++ ) {
                double extra = (dp[i][j]-1)/2.0;
                if(extra > 0) {
                    dp[i+1][j] += extra;
                    dp[i+1][j+1] += extra;
                }
            }
        }
        // dp[rows][glass] > 1, 
        return Math.min(1, dp[rows][glass]);
        
    }
}
/*
0
0 1
0 1 2
0 1 2 3
*/