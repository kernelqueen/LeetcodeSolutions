class Solution {

    public int minCost(int n, int[][] edges) {
        // Build adjacency list; reverse edge has double cost
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            graph.computeIfAbsent(src, k -> new ArrayList<>())
                 .add(new int[]{dest, cost});

            graph.computeIfAbsent(dest, k -> new ArrayList<>())
                 .add(new int[]{src, 2 * cost});
        }

        return dijkstra(n, graph);
    }

    private int dijkstra(int n, Map<Integer, List<int[]>> graph) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // Min-heap to always process the node with the smallest cost so far
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        boolean[] visited = new boolean[n];
        pq.offer(new int[]{0, 0}); // {node, cost}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int costSoFar = curr[1];

            if (node == n - 1) return costSoFar;
            if (visited[node]) continue;
            visited[node] = true;

            for (int[] edge : graph.getOrDefault(node, Collections.emptyList())) {
                int next = edge[0];
                int newCost = costSoFar + edge[1];

                if (newCost < dist[next]) {
                    dist[next] = newCost;
                    pq.offer(new int[]{next, newCost});
                }
            }
        }
        return -1;
    }
}

/*
Time Complexity: O((V + E) log V)
Space Complexity: O(V + E)
*/
