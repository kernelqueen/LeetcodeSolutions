class Solution {
    int dirs[][] = {{-1,0}, {0,-1}, {1,0},{0,1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean visited[][] = new boolean[n][n];

        // Min-heap based on the current water level
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[2]-b[2]);

        pq.offer(new int[]{0,0,grid[0][0]});
        visited[0][0] = true;

        // Dijkstra-like BFS
        while(!pq.isEmpty()) {
            int cell[] = pq.poll();
            int cx = cell[0], cy = cell[1], h = cell[2];

            for(int dir[] : dirs) {
                int x = cx + dir[0];
                int y = cy + dir[1];
                if(x<0 || x>=n || y<0 || y>=n || visited[x][y]) continue;

                int height = grid[x][y];
                int newHeight = Math.max(h, height);
                visited[x][y] = true; 

                if(x == n-1 && y == n-1) return newHeight;

                pq.offer(new int[]{x, y, newHeight});
                
            }
        }

        return 0;
    }
}
