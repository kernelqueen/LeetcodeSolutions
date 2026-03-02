class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int zeros[] = new int[n]; // O(n)
        // O(n^2)
        for(int i=0; i<n; i++) {
            int count  = 0;
            for(int j=n-1; j>=0; j--) {
                if(grid[i][j]==0)
                    count++;
                else break;
            }
            zeros[i] = count;
        }

        int swaps = 0;
        // O(n)*(n+n) = O(n^2)
        for(int i=0; i<n; i++) {
            int need = n-1-i;
            int j = i;
            while(j<n && zeros[j] < need)
                j++;
            if(j==n)
                return -1;
            while(j>i) {
                int temp = zeros[j];
                zeros[j] = zeros[j-1];
                zeros[j-1] = temp;
                swaps++;
                j--;
            }
        }
        return swaps;
    }
}