class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        int presum[][] = new int[m+1][n+1];

        boolean seen[] = new boolean[n+1]; 
        // O(m*n)
        //O(m*n)
        for(int i=1; i<=m; i++) {
            boolean seenrow = false;
            for(int j=1; j<=n; j++) {
                char ch = grid[i-1][j-1];
                if(ch=='X') {
                    seen[j] = true;
                }
                if(seen[j]==true)
                    seenrow = true;
                /*

                . . X
                X . .
                Y . X
                Y Y .

                */

                int val = (ch=='X') ? 1 : (ch=='Y') ? -1 : 0;

                presum[i][j] = val + presum[i-1][j] +
                presum[i][j-1] - presum[i-1][j-1];

                if(presum[i][j]==0 && (seen[j] || seenrow))
                    ans++;                    
                
            }
        }
        return ans;
        

    }
}