/*DFS Solution */
class Solution {
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // DFS from the ocean borders
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);       // left (Pacific)
            dfs(heights, atlantic, i, n - 1, heights[i][n-1]); // right (Atlantic)
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);        // top (Pacific)
            dfs(heights, atlantic, m - 1, j, heights[m-1][j]); // bottom (Atlantic)
        }

        // Find common cells
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }

    private void dfs(int[][] h, boolean[][] visited, int i, int j, int prevHeight) {
        int m = h.length, n = h[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || h[i][j] < prevHeight)
            return;
        visited[i][j] = true;
        for (int[] d : DIRS)
            dfs(h, visited, i + d[0], j + d[1], h[i][j]);
    }
}

/*BFS Solution */
class Solution {
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pacQueue = new LinkedList<>();
        Queue<int[]> atlQueue = new LinkedList<>();

        // Add border cells for both oceans
        for (int i = 0; i < m; i++) {
            pacQueue.offer(new int[]{i, 0});
            atlQueue.offer(new int[]{i, n - 1});
            pacific[i][0] = atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pacQueue.offer(new int[]{0, j});
            atlQueue.offer(new int[]{m - 1, j});
            pacific[0][j] = atlantic[m - 1][j] = true;
        }

        bfs(heights, pacQueue, pacific);
        bfs(heights, atlQueue, atlantic);

        // Find common reachable cells
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }

    private void bfs(int[][] h, Queue<int[]> q, boolean[][] visited) {
        int m = h.length, n = h[0].length;
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int i = cell[0], j = cell[1];
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1];
                if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || h[x][y] < h[i][j])
                    continue;
                visited[x][y] = true;
                q.offer(new int[]{x, y});
            }
        }
    }
}
