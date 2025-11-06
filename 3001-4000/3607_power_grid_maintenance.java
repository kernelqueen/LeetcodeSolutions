/*----- Mapping Array and Sorted Set(TreeSet) Approach */
class Solution {
    HashMap<Integer, List<Integer>> graph;
    int[] mapping;
    // mapping array + sorted set
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        graph = new HashMap<>();
        // graph in adjacency list format
        for (int i = 1; i <= c; i++) {
            graph.put(i, new ArrayList<>());
        }
        // 1 - [2]
        // 2 - [1,3]
        // 3 - [2,4]
        // 4 - [3,5]
        // 5 - [3]

        for (int[] connection : connections) {
            int src = connection[0];
            int dest = connection[1];
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }

        int curr = 1; // gridvalue / component 
        mapping = new int[c + 1];

        for (int i = 1; i <= c; i++) {
            if (mapping[i] == 0) {
                dfs(i, curr);
                curr++;
            }
        }   
        // [1,1,1,1,1]

        HashMap<Integer, TreeSet<Integer>> onlinegrid = new HashMap<>();
        // 1 --> [1,2,3,4,5]
        for (int i = 1; i <= c; i++) {
            int gridvalue = mapping[i];
            onlinegrid.putIfAbsent(gridvalue, new TreeSet<>());
            onlinegrid.get(gridvalue).add(i);
        }

        List<Integer> ans = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0];
            int node = query[1];

            int gridvalue = mapping[node];
            TreeSet<Integer> set = onlinegrid.get(gridvalue);

            if (type == 1) {
                if (set.contains(node)) {
                    ans.add(node);
                } else {
                    ans.add(set.isEmpty() ? -1 : set.first());
                }
            } else if (type == 2) {
                set.remove(node);
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    void dfs(int node, int gridvalue) {
        if (mapping[node] != 0)
            return;
        mapping[node] = gridvalue;
        for (int nei : graph.get(node)) {
            if (mapping[nei] == 0) {
                dfs(nei, gridvalue);
            }
        }
    }
}

/*----- Union Find and Priority Queue Approach */
class Solution {
    // union find + priority queue
    int[] parent;
    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); 
        return parent[x];
    }

    public boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py)
            return false;
        parent[py] = px;
        return true;
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        parent = new int[c + 1];
        for (int i = 0; i <= c; i++)
            parent[i] = i;

        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        for (int[] conn : connections)
            union(conn[0], conn[1]);

        Map<Integer, PriorityQueue<Integer>> componentHeap = new HashMap<>();
        for (int station = 1; station <= c; station++) {
            int root = find(station);
            componentHeap.putIfAbsent(root, new PriorityQueue<>());
            componentHeap.get(root).offer(station);
        }

        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0], node = query[1];

            if (type == 2) {
                online[node] = false;
            } else {
                if (online[node]) {
                    result.add(node);
                } else {
                    int root = find(node);
                    PriorityQueue<Integer> heap = componentHeap.get(root);

                    while (heap != null && !heap.isEmpty() && !online[heap.peek()]) {
                        heap.poll(); // lazy deletion
                    }

                    result.add(heap.isEmpty() ? -1 : heap.peek());
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}