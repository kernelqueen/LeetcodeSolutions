class Solution {

    // Counts how many 3x3 magic squares exist inside the grid
    public int numMagicSquaresInside(int[][] grid) {

        int m = grid.length;        // number of rows
        int n = grid[0].length;     // number of columns
        int count = 0;              // total magic squares found

        // Iterate over all possible 3x3 sub-grids
        for (int i = 0; i <= m - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {

                // Check if the current 3x3 grid is a magic square
                if (isMagic(i, j, grid)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Checks whether the 3x3 sub-grid starting at (row, col) is a magic square
    boolean isMagic(int row, int col, int[][] grid) {

        // Used to ensure all numbers 1–9 appear exactly once
        boolean[] seen = new boolean[10]; // index 1–9 used

        // Validate numbers: must be unique and between 1 and 9
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int cell = grid[row + i][col + j];

                // If number is out of range or already used → not magic
                if (cell < 1 || cell > 9 || seen[cell]) {
                    return false;
                }
                seen[cell] = true;
            }
        }

        // Check all row sums (each must equal 15)
        for (int i = 0; i < 3; i++) {
            if (grid[row + i][col] +
                grid[row + i][col + 1] +
                grid[row + i][col + 2] != 15) {
                return false;
            }
        }

        // Check all column sums (each must equal 15)
        for (int j = 0; j < 3; j++) {
            if (grid[row][col + j] +
                grid[row + 1][col + j] +
                grid[row + 2][col + j] != 15) {
                return false;
            }
        }

        // Check main diagonal (top-left → bottom-right)
        if (grid[row][col] +
            grid[row + 1][col + 1] +
            grid[row + 2][col + 2] != 15) {
            return false;
        }

        // Check secondary diagonal (bottom-left → top-right)
        if (grid[row + 2][col] +
            grid[row + 1][col + 1] +
            grid[row][col + 2] != 15) {
            return false;
        }

        // All checks passed → this is a magic square
        return true;
    }
}
