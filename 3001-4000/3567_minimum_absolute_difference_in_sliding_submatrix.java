class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int ans[][] = new int[m-k+1][n-k+1];

        // (m-k)(n-k)*(k*k + k*k*logk)

        for(int i=0; i<m-k+1; i++) {
            for(int j=0; j<n-k+1; j++) {

                TreeSet<Integer> tset = new TreeSet<>();
                for(int r=i; r<i+k; r++) {
                    for(int c=j; c<j+k; c++) {
                        tset.add(grid[r][c]);
                    }
                }

                int min = Integer.MAX_VALUE;
                // 1 2 3 4 5
                int prev = tset.first();
                for(int val : tset) {
                    if(val!=prev) {
                        min = Math.min(min, val-prev);
                        prev = val;
                    }
                }

                ans[i][j] = min==Integer.MAX_VALUE ? 0 : min;
            }
        }

        return ans;
    }
}