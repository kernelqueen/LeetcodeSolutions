class Solution {
    public int trapRainWater(int[][] blocks) {

        int dirs[][] = {{0,1},{1,0},{-1,0},{0,-1}};
    
        int m = blocks.length, n = blocks[0].length;
        boolean visited[][] = new boolean[m][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> Integer.compare(a[0],b[0])); // height, row, column

        for(int i=0; i<m; i++) {
            pq.offer(new int[]{blocks[i][0],i,0});
            pq.offer(new int[]{blocks[i][n-1],i,n-1});
            visited[i][0] = visited[i][n-1] = true;
        }

        for(int i=0; i<n; i++) {
            pq.offer(new int[]{blocks[0][i],0,i});
            pq.offer(new int[]{blocks[m-1][i],m-1,i});
            visited[0][i] = visited[m-1][i] = true;
        }

        int water = 0;

        while(!pq.isEmpty()) {
            int curr[] = pq.remove();
            int currHeight = curr[0] , r = curr[1], c = curr[2];

            for(int dir[] : dirs) {
                int row = dir[0] + r;
                int col = dir[1] + c;
                if(row>=0 && col>=0 && row<m && col<n && !visited[row][col]) {
                    int height = blocks[row][col];
                    if(height < currHeight) {
                        water += currHeight-height;
                    }
                    int newboundary[] = {Math.max(height, currHeight), row, col};
                    pq.offer(newboundary);
                    visited[row][col] = true;
                } 
            }
        }

        return water;

    }
}