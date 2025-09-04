/*--------------- Recursive Solution ---------------*/
class Solution {

    int dirs[][] = {{-1,1},{1,1},{1,-1},{-1,-1}};

    public int lenOfVDiagonal(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        int maxlen = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1) {
                    maxlen = Math.max(maxlen, 1);
                    maxlen = Math.max(maxlen, dfs(i, j, 0, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 1, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 2, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 3, 2, grid, false));
                }
            }
        }

        return maxlen;

    }

    public int dfs(int row, int col, int dir, int nextval, int grid[][], boolean turn) {
        int i = row + dirs[dir][0];
        int j = col + dirs[dir][1];

        int m = grid.length, n = grid[0].length;
        // base case
        if(i<0 || j<0 || i>=m || j>=n || grid[i][j]!=nextval) {
            return 1;
        }

        int straightValue = 1 + dfs(i, j, dir, nextval==2? 0 : 2, grid, turn);
        int turnValue = 0;
        if(!turn) {
            turnValue = 1 + dfs(i, j, (dir+1)%4, nextval==2? 0 : 2, grid, true);
        }

        return Math.max(straightValue, turnValue);
    }
}


/*--------------- DP Solution ---------------*/

class Solution {

    int dirs[][] = {{-1,1},{1,1},{1,-1},{-1,-1}};
    int dp[][][][];

    public int lenOfVDiagonal(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        dp = new int[m][n][4][2]; // row, cols, dir, turn
        int maxlen = 0;

        // dp initialization
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                for(int d=0; d<4; d++) {
                    for(int t=0; t<2; t++) {
                        dp[i][j][d][t] = -1;
                    }
                }
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1) {
                    maxlen = Math.max(maxlen, 1);
                    maxlen = Math.max(maxlen, dfs(i, j, 0, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 1, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 2, 2, grid, false));
                    maxlen = Math.max(maxlen, dfs(i, j, 3, 2, grid, false));
                }
            }
        }

        return maxlen;

    }

    public int dfs(int row, int col, int dir, int nextval, int grid[][], boolean turn) {

        int turnIndex = turn ? 1 : 0;

        if(dp[row][col][dir][turnIndex]!=-1)
            return dp[row][col][dir][turnIndex];

        int i = row + dirs[dir][0];
        int j = col + dirs[dir][1];

        int m = grid.length, n = grid[0].length;
        // base case
        if(i<0 || j<0 || i>=m || j>=n || grid[i][j]!=nextval) {
            return 1;
        }

        int straightValue = 1 + dfs(i, j, dir, nextval==2? 0 : 2, grid, turn);
        int turnValue = 0;
        if(!turn) {
            turnValue = 1 + dfs(i, j, (dir+1)%4, nextval==2? 0 : 2, grid, true);
        }

        return dp[row][col][dir][turnIndex] = Math.max(straightValue, turnValue);
    }
}


/*

DP understanding example
1 0 0 0 0 
0 2 0 0 0 
0 0 0 0 1
0 0 0 2 2
0 0 0 0 0

*/
