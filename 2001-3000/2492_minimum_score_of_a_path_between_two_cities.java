class Solution {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> hmap = new HashMap<>();

        for(int[] road : roads) {
            // O(E)
            int src = road[0], dest = road[1], dist = road[2];
            if(!hmap.containsKey(src))
                hmap.put(src, new ArrayList<>());
            if(!hmap.containsKey(dest))
                hmap.put(dest, new ArrayList<>());
            hmap.get(src).add(new int[]{dest, dist});
            hmap.get(dest).add(new int[]{src, dist});
        }

        int ans = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        // O(V)
        Set<Integer> visited = new HashSet<>();

        q.offer(1);
        visited.add(1);

        // BFS
        while(!q.isEmpty()) {
            int node = q.remove();
            List<int[]> list = hmap.get(node);
            for(int next[] : list) {
                
                ans = Math.min(ans, next[1]);
                if(!visited.contains(next[0])) {
                    q.offer(next[0]);
                    visited.add(next[0]);
                }

            }
        }

        return ans;
    }
}