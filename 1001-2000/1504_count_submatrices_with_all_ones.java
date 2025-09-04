class Solution {
    public int numSubmat(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;

        int ans = 0;

        int[][] width = new int[m][n];

        // Filling the width matrix for all rows

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j]==1) {
                    width[i][j] = (j==0 ? 1 : width[i][j-1]+1); 
                }
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                int minwidth = width[i][j];
                for(int k=i; k>=0 && minwidth>0; k--) {
                    minwidth = Math.min(minwidth, width[k][j]); 
                    ans += minwidth; // 1 + 1 + (1 + 1) + 2 + (1 + 1 + 1) + (2 + 2) = 13
                }
            }
        }

        return ans;

    }
}
