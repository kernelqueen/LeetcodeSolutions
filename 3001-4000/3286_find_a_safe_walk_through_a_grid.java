class Solution {
    int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    public boolean findSafeWalk(List<List<Integer>> arr, int health) {
        int m = arr.size(), n = arr.get(0).size();
        int[][] grid = arr.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
        // [costsofar, i, j]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0])); // Min heap

        pq.offer(new int[]{grid[0][0],0,0});
        grid[0][0] = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            // O(m*n logmn)
            // O(m*n)
            int curr[] = pq.remove();
            int cost = curr[0], x = curr[1], y = curr[2];

            if(x==m-1 && y==n-1)
                return true;

            for(int d[] : dir) {
                int r = x + d[0];
                int c = y + d[1];
                if(r<0 || r>=m || c<0 || c>=n || health-grid[r][c]<=0)
                    continue;
                int nextcost = cost + grid[r][c];
                if(nextcost < health) 
                    pq.offer(new int[]{nextcost, r, c});
                grid[r][c] = Integer.MAX_VALUE;
            }
        }

        return false;


    }
}