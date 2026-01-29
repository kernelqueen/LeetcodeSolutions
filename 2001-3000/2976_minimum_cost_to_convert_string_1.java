/***********Dijkstra's Algorithm Approach************** */
class Solution {

    // Adjacency list: fromChar -> list of {toChar, conversionCost}
    private Map<Integer, List<int[]>> adjacency;

    public long minimumCost(
            String source,
            String target,
            char[] original,
            char[] changed,
            int[] cost
    ) {
        buildGraph(original, changed, cost);

        long totalCost = 0;

        // minCost[u][v] = minimum cost to convert char u -> char v
        // -1 means not computed / unreachable
        long[][] minCost = new long[26][26];
        for (long[] row : minCost) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < source.length(); i++) {
            int fromChar = source.charAt(i) - 'a';
            int toChar = target.charAt(i) - 'a';

            // If we already know all distances from fromChar, reuse them
            if (minCost[fromChar][toChar] == -1) {
                minCost[fromChar] = dijkstraFromSource(fromChar);
            }

            if (minCost[fromChar][toChar] == -1) {
                return -1L; // conversion impossible
            }

            totalCost += minCost[fromChar][toChar];
        }

        return totalCost;
    }

    // Build directed weighted graph from conversion rules
    private void buildGraph(char[] original, char[] changed, int[] cost) {
        adjacency = new HashMap<>();

        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            int conversionCost = cost[i];

            adjacency
                .computeIfAbsent(from, k -> new ArrayList<>())
                .add(new int[]{to, conversionCost});
        }
    }

    // Single-source shortest paths from srcChar to all other characters
    private long[] dijkstraFromSource(int srcChar) {
        long[] distance = new long[26];
        Arrays.fill(distance, Long.MAX_VALUE);

        // {node, currentCost}
        PriorityQueue<long[]> minHeap =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        distance[srcChar] = 0;
        minHeap.offer(new long[]{srcChar, 0});

        boolean[] visited = new boolean[26];

        while (!minHeap.isEmpty()) {
            long[] current = minHeap.poll();
            int node = (int) current[0];
            long currentCost = current[1];

            if (visited[node]) continue;
            visited[node] = true;

            List<int[]> neighbors = adjacency.get(node);
            if (neighbors == null) continue;

            for (int[] edge : neighbors) {
                int nextNode = edge[0];
                long newCost = currentCost + edge[1];

                if (newCost < distance[nextNode]) {
                    distance[nextNode] = newCost;
                    minHeap.offer(new long[]{nextNode, newCost});
                }
            }
        }

        // Normalize unreachable nodes to -1
        for (int i = 0; i < 26; i++) {
            if (distance[i] == Long.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        return distance;
    }
}


/************Floyd Warshall Algorithm Approach************ */
class Solution {
    public long minimumCost(String source, String target,
     char[] original, char[] changed, int[] cost) {
        long ans = 0;
        // source.length()==n, original = k
        long mincost[][] = new long[26][26]; // O(1)

        for(long c[] : mincost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }
        // O(k)
        for(int i=0; i<original.length; i++) {
            int src = original[i]-'a'; // 0 - 25
            int dest = changed[i]-'a';
            mincost[src][dest] = Math.min(mincost[src][dest], cost[i]);
        }

        // Floydd Warshall algo
        // O(26*26*26)
        for(int k=0; k<26; k++) {
            for(int i=0; i<26; i++) {
                for(int j=0; j<26; j++) {
                    mincost[i][j] = Math.min(mincost[i][j], mincost[i][k] + mincost[k][j]);
                }
            }
        }
        // O(n)
        for(int i=0; i<source.length(); i++) {
            int s = source.charAt(i)-'a';
            int d = target.charAt(i)-'a';
            if(s==d)
                continue;
            if(mincost[s][d]==Integer.MAX_VALUE)
                return -1;
            ans += mincost[s][d];
            
        }
        return ans;


    }
}