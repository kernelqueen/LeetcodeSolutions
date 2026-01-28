/**************Recursive DP Solution************** */

class Solution {
    int m,n;
    int tarr[][];
    Integer dp[][][];
    public int minCost(int[][] grid, int k) {

        this.m = grid.length;
        this.n = grid[0].length;
        tarr = new int[m*n][3]; 
        dp = new Integer[m][n][k+1];
        int index = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                tarr[index][0] = grid[i][j];
                tarr[index][1] = i;
                tarr[index][2] = j;
                index++;
            }
        }
        Arrays.sort(tarr, (a,b)->Integer.compare(a[0],b[0]));
        
        return helper(grid,0,0,k);

    }
    // helper(i, j, t) =minimum cost to reach destination from (i,j) with t teleports remaining
    int helper(int grid[][], int i, int j, int t) {
        if(i==m-1 && j==n-1) {
            return 0;
        }
        if(i>=m || j>=n) {
            return Integer.MAX_VALUE; // can not reach to solution from here
        }
        if(dp[i][j][t]!=null)
            return dp[i][j][t];

        int ans = Integer.MAX_VALUE;
        int right = ( j < n-1) ?grid[i][j+1] : Integer.MAX_VALUE;
        int down = (i<m-1) ? grid[i+1][j] : Integer.MAX_VALUE;
        if(j+1 < n) {
            ans = Math.min(ans, grid[i][j+1] + helper(grid, i, j+1, t));
        }
        if(i+1 < m) {
            ans = Math.min(ans, grid[i+1][j] + helper(grid, i+1, j, t));
        }

        if(t > 0) {
            for(int in=0; in<tarr.length; in++) {
                if(tarr[in][0] <= grid[i][j]) {
                    ans = Math.min(ans, helper(grid, tarr[in][1], tarr[in][2], t-1));
                }
                else break;
            }
        }

        return dp[i][j][t] = ans;
    }
}

/**************Optimal DP Solution************** */
class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int cells[][] = new int[m*n][2]; 
        int index = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                cells[index][0] = i;
                cells[index][1] = j;
                index++;
            }
        }
        // T.C : O(m*n*log(m*n))
        Arrays.sort(cells, (a,b) -> Integer.compare(grid[a[0]][a[1]], grid[b[0]][b[1]]));

        int cost[][] = new int[m][n]; // cost to reach destination from current cell 

        for(int c[] : cost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }
        // TC: O(k*m*n)
        for(int t=0; t<k+1; t++) { // 0,1,2
            // updating cost using teleportation
            int mincost = Integer.MAX_VALUE;
            int start = 0;
            for(int i=0; i<cells.length; i++) {
                int x = cells[i][0];
                int y = cells[i][1];
                mincost = Math.min(mincost, cost[x][y]);
                if(i+1 < cells.length && grid[x][y]==grid[cells[i+1][0]][cells[i+1][1]])
                    continue;
                // start to i - update cost
                for(int j=start; j<=i; j++) {
                    cost[cells[j][0]][cells[j][1]] = mincost;
                }
                start = i+1;
            }

            for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(i==m-1 && j==n-1) {
                    cost[i][j] = 0;
                }

                // Move down
                if(i!=m-1) {
                    cost[i][j] = Math.min(cost[i][j], grid[i+1][j] + cost[i+1][j]);
                }

                // Move right
                if(j!=n-1) {
                    cost[i][j] = Math.min(cost[i][j], grid[i][j+1] + cost[i][j+1]);
                }
            }
        }
        }

     
     return cost[0][0];
    }
}