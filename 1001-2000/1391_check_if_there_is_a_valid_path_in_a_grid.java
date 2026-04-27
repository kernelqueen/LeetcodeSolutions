class Solution {
    
    int m,n;
    boolean visited[][];
    // 1,2,3,4,5,6
    boolean canRight[] = {false, true, false, false, true, false, true};
    boolean canLeft[] = {false, true, false, true, true, true, false};
    boolean canUp[] = {false, false, true, false, false, true, true};
    boolean canDown[] = {false, false, true, true, true, false, false};
    int dirs[][] = {{0,1},{0,-1},{-1,0},{1,0}}; // right, left, up, down
    public boolean hasValidPath(int[][] grid) {
        // O(m*n)
        // O(m*n)
        m = grid.length; n = grid[0].length;
        visited = new boolean[m][n];
        return dfs(0,0,grid);
    }

    boolean dfs(int r, int c, int[][] grid) {
        if(r==m-1 && c==n-1)
            return true;
        visited[r][c] = true;

        for(int i=0; i<4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if(nr>=0 && nr<m && nc>=0 && nc<n && !visited[nr][nc] &&
             canMove(grid[r][c], grid[nr][nc], i)) {
                if(dfs(nr, nc, grid))
                    return true;
             }
        }

        return false;

    }

    boolean canMove(int curr, int next, int dir) {
        if(dir==0) { // right
            return canRight[curr] && canLeft[next];
        } else if(dir==1) { // left
            return canLeft[curr] && canRight[next];
        } else if(dir==2) { // up
            return canUp[curr] && canDown[next];
        } else if(dir==3) { // down
            return canDown[curr] && canUp[next];
        }

        return false;
    }
}