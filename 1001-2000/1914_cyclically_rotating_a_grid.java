class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int r1 = 0, r2=m-1, c1=0, c2=n-1; // Outer layer or ring

        while(r1< r2 && c1<c2) {
            int total = (r2-r1)*2 + (c2-c1)*2;
            int shift = k%total;
            int ring[] = new int[total]; // O(m+n)
            // O(m*n)
            int idx = 0;
            // top
            for(int j=c1; j<c2; j++)
                ring[idx++] = grid[r1][j];
            // right
            for(int i=r1; i<r2; i++)
                ring[idx++] = grid[i][c2];
            // bottom
            for(int j=c2; j>c1; j--)
                ring[idx++] = grid[r2][j];
            // left
            for(int i=r2; i>r1; i--)
                ring[idx++] = grid[i][c1];
            
            // shifting ring array
            reverse(ring, 0, shift-1);
            reverse(ring, shift, total-1);
            reverse(ring, 0, total-1);
            idx = 0;
            for(int j=c1; j<c2; j++)
              grid[r1][j] = ring[idx++];
            // right
            for(int i=r1; i<r2; i++)
                grid[i][c2] = ring[idx++];
            // bottom
            for(int j=c2; j>c1; j--)
                grid[r2][j] = ring[idx++];
            // left
            for(int i=r2; i>r1; i--)
                grid[i][c1] = ring[idx++];
            r1++;
            r2--;
            c1++;
            c2--;
        }

        return grid;
    }

    void reverse(int arr[], int l, int r) {
        while(l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}