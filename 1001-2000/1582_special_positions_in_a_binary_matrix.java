class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // O(m*n)
        // O(m+n)
        int rows[] = new int[m];
        int cols[] = new int[n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j]==1)
                {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        int ans = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(rows[i]==1 && cols[j]==1 && mat[i][j]==1)
                    ans++;
            }
        }

        return ans;
    }
}
/*
1 0 0
0 1 0
0 0 1

rows[] = [1,1,1]
cols[] = [1,1,1]

*/