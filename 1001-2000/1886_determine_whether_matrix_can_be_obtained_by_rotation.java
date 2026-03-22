class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean r1 = true, r2 = true, r3 = true, r4 = true;
        int n = mat.length;
        // O(n^2)
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // 0 degrees or 360 degree rotation
                if(mat[i][j]!=target[i][j])
                    r1 = false;
                // 90 degree
                if(mat[i][j]!=target[j][n-i-1])
                    r2 = false;
                // 180 degree
                if(mat[i][j]!=target[n-i-1][n-j-1])
                    r3 = false;
                // 270
                if(mat[i][j]!=target[n-j-1][i])
                    r4 = false;
            }
        }
         return (r1 || r2 || r3 || r4);
    }
}