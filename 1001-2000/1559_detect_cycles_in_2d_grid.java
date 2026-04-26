class Solution {
    boolean visited[][];
    int dirs[][] = {{0,1},{1,0},{-1,0},{0,-1}};
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        visited = new boolean[m][n];

        // grid traversal
        // O(m*n)
        // O(m*n)
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                
                if(!visited[i][j] && dfs(-1, -1, i, j, grid[i][j], grid))
                    return true; // cycle found
            }
        }

        return false;
    }

    boolean dfs(int pr, int pc, int r, int c, char ch, char grid[][]) {
        // valid cell or not
        if(r>=grid.length || r<0 || c>=grid[0].length || c<0 || grid[r][c]!=ch)
            return false;
        if(visited[r][c]==true)
            return true;
        
        visited[r][c] = true;
        for(int dir[] : dirs) {
            int x = r + dir[0];
            int y = c + dir[1];
            if(x==pr && y==pc)
                continue;
            if(dfs(r,c,x,y, ch, grid)) 
                return true;
        }

        return false;
        
    }
} 