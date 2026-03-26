class Solution {
    int m,n;
    long totalsum;
    int [][] grid;
    HashMap<Long, List<int[]>> hmap; // O(m*n)
    public boolean canPartitionGrid(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        hmap = new HashMap<>();
        // m*n
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                totalsum += grid[i][j];
                long val = grid[i][j];
                if(!hmap.containsKey(val)) {
                    hmap.put(val, new ArrayList<>());
                }
                hmap.get(val).add(new int[]{i,j});
            }
        }

        // horizonal cut
        long currsum = 0;
        // m*(n+m*n) + n*(m+m*n)
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++)
                currsum +=grid[i][j];
            if(isValidPartition(currsum, 
            totalsum-currsum, i, true))
                return true;
        }

        // vertical cut
        currsum = 0;
        for(int j=0; j<n; j++) {
            for(int i=0; i<m; i++)
                currsum +=grid[i][j];
            if(isValidPartition(currsum, 
            totalsum-currsum, j, false))
                return true;
        }

        return false;
    }

    boolean isValidPartition(long sumA, long sumB, int index
    , boolean rowcut) {
        // O(m*n)
        if(sumA==sumB)
            return true;
        long diff = sumA-sumB;
        if(!hmap.containsKey(Math.abs(diff)))
            return false;
        List<int[]> indices = hmap.get(Math.abs(diff));

        // sec A, sec B
        boolean canRemove = false;
        for(int[] idx : indices) {
            int r = idx[0], c = idx[1];

            // in which section idx lies
            // also depends upon if its a rowcut
            // or column cut
            boolean inA = rowcut ? r<=index : c<=index;

            // 10 --- 12, diff = 2
            // diff = SecA-SecB
            if(diff > 0 && !inA)
                continue;
            if(diff<0 && inA)
                continue;
            
            // we will be in the correct section
            /*
            _ _ _ _
            _ _ _ _
            _ _ _ _
            _ _ _ _

            */
            int minR, maxR, minC, maxC;

            if(inA) {
                minR = 0;
                minC = 0;
                maxR = rowcut ? index : m-1;
                maxC = rowcut ? n-1 : index;
            }
            else {
                maxR = m-1;
                maxC = n-1;
                minR = rowcut ? index + 1 : 0;
                minC = rowcut ? 0 : index+1;
            }

            int rows = maxR-minR+1;
            int cols = maxC-minC+1;

            // idx -- [r,c]
            if(rows==1) {
                canRemove = (c==minC || c==maxC);
            }
            else if(cols==1) {
                canRemove = (r==minR || r==maxR);
            }
            else {
                canRemove = true;
            }

        }
        return canRemove;
    }
}