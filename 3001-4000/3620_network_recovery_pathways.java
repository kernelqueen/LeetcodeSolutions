class Solution {
    HashMap<Integer, List<int[]>> hmap;
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        
         hmap = new HashMap<>();
        int n = online.length;

        int maxedge = 0;

        for(int edge[] : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            if(online[src]==false || online[dest]==false)
                continue;
            if(!hmap.containsKey(src))
                hmap.put(src, new ArrayList<>());

            maxedge = Math.max(maxedge, cost);
            
            int[] temp = {dest, cost};
            
            hmap.get(src).add(temp);
        }

        int left = 0, right = maxedge;
        int ans = -1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(dijkstra(mid, k, n)) {
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }           
        }

        return ans;

    }

    
    boolean dijkstra(int maxedge, long k, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        q.offer(new long[]{0, 0});

        while (!q.isEmpty()) {
            long[] curr = q.remove();
            int node = (int) curr[0];
            long costsofar = curr[1];

            if (costsofar > dist[node]) continue;   // stale entry, skip
            if (node == n - 1) return costsofar <= k;

            List<int[]> list = hmap.get(node);
            if (list == null) continue;

            for (int[] next : list) {
                if (next[1] < maxedge) continue;      // edge below threshold
                long nextcost = costsofar + next[1];
                if (nextcost > k) continue;           // budget pruning
                if (nextcost < dist[next[0]]) {
                    dist[next[0]] = nextcost;
                    q.offer(new long[]{next[0], nextcost});
                }
            }
        }
        return false;
    }
}








