class Solution {
    public int largestSubmatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        // O(m * (n + nlogn + n))
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i>0 && grid[i][j]>0)
                    grid[i][j] += grid[i-1][j];
            }
            int temp[] = grid[i].clone(); // O(n)
            Arrays.sort(temp);
            for(int k=0; k<n; k++)
            {
                int width = n-k;
                ans = Math.max(ans, temp[k]*width);
            }
        }
        return ans;
    }
}