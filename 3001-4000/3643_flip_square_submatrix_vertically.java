class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int m = grid.length, n = grid[0].length;
        int top = x, bottom = x+k-1;
        // O(k*k)

        while(top < bottom) {
            for(int j=y; j<y+k; j++) {
                int temp = grid[top][j];
                 grid[top][j] = grid[bottom][j];
                 grid[bottom][j] = temp;
            }
            top++;
            bottom--;
        }

        return grid;

    }
}