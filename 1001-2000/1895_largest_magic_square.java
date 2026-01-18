class Solution {
    // prefix sum array
    int[][] rowPref, colPref, diag1Pref, diag2Pref;
    public int largestMagicSquare(int[][] grid) {
        // 2 -- Math.min(m, n)

        int m = grid.length, n = grid[0].length;

        buildPrefix(grid); // o(m*n)

        int max = 1; // 1*1 is a magic square
        // O(Min(m,n)*m*n*(m+n))
        // O(m*n)
        for(int len = 2; len <= Math.min(m,n); len++) {
            for(int row=0; row+len<=m; row++) {
                for(int col=0; col+len<=n; col++) {
                    if(checkMagic(row, col, len)) {
                        max = Math.max(max, len);
                    }
                }
            }
        }

        return max;
    }

    void buildPrefix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        rowPref = new int[m][n+1];
        colPref = new int[m+1][n];
        diag1Pref = new int[m+1][n+1];
        diag2Pref = new int[m+1][n+1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPref[i][j + 1] = rowPref[i][j] + grid[i][j];
                colPref[i + 1][j] = colPref[i][j] + grid[i][j];
                diag1Pref[i + 1][j + 1] = diag1Pref[i][j] + grid[i][j];
                diag2Pref[i + 1][j] = diag2Pref[i][j + 1] + grid[i][j];
            }
        }
    }

    boolean checkMagic(int row, int col, int side) {
        int target = rowPref[row][col + side] - rowPref[row][col];

        // rows 
        for (int i = row; i < row + side; i++) {
            if (rowPref[i][col + side] - rowPref[i][col] != target)
                return false;
        }

        // cols 
        for (int j = col; j < col + side; j++) {
            if (colPref[row + side][j] - colPref[row][j] != target)
                return false;
        }

        // diagonal

        if (diag1Pref[row + side][col + side] - diag1Pref[row][col] != target)
            return false;

        if (diag2Pref[row + side][col] - diag2Pref[row][col + side] != target)
            return false;

        return true;


    }
}






