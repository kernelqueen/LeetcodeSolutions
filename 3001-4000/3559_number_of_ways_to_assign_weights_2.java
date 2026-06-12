class Solution {
    int mod = 1_000_000_007;
    int[] depth;
    HashMap<Integer, List<Integer>> hmap;
    int[][] lift; // Binary lifting table: lift[i][j] = 2^j-th ancestor of node i

    // Time:  O(n) build adjacency + O(n) BFS + O(n log n) lift table + O(q log n) queries
    // Space: O(n log n) for lift table, O(n) for depth/visited

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        depth = new int[n + 1];
        hmap = new HashMap<>();
        lift = new int[n + 1][18]; // 2^18 > 10^5, sufficient for any tree of this size

        // Build undirected adjacency list
        for (int[] e : edges) {
            int s = e[0], d = e[1];
            hmap.putIfAbsent(s, new ArrayList<>());
            hmap.putIfAbsent(d, new ArrayList<>());
            hmap.get(s).add(d);
            hmap.get(d).add(s);
        }

        // BFS from root (node 1) to compute depth and direct parents (lift[i][0])
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.offer(1);
        visited[1] = true;
        lift[1][0] = 1; // Root's parent points to itself (sentinel)

        while (!q.isEmpty()) {
            int curr = q.remove();
            List<Integer> children = hmap.get(curr);
            if (children == null) continue;
            for (int next : children) {
                if (visited[next]) continue;
                visited[next] = true;
                depth[next] = depth[curr] + 1;
                lift[next][0] = curr; // Direct parent
                q.offer(next);
            }
        }

        // Build binary lifting table bottom-up
        // lift[i][j] = 2^j-th ancestor = 2^(j-1)-th ancestor of 2^(j-1)-th ancestor
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < 18; j++) {
                lift[i][j] = lift[lift[i][j - 1]][j - 1];
            }
        }

        // Answer each query using LCA
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int dist = depth[u] + depth[v] - 2 * depth[lca(u, v)];
            // Number of valid assignments = 2^(dist-1); 0 edges means same node
            ans[i] = (dist == 0) ? 0 : power(2, dist - 1);
        }

        return ans;
    }

    // LCA via binary lifting
    int lca(int u, int v) {
        // Ensure u is always the deeper node
        if (depth[u] < depth[v]) return lca(v, u);

        // Step 1: Bring u up to the same level as v
        int diff = depth[u] - depth[v];
        for (int j = 0; j < 18; j++) {
            if ((diff & 1) == 1)
                u = lift[u][j];
            diff >>= 1;
        }

        if (u == v) return u; // Same node after leveling — LCA found

        // Step 2: Move both up together until their ancestors diverge
        for (int j = 17; j >= 0; j--) {
            if (lift[u][j] != lift[v][j]) {
                u = lift[u][j];
                v = lift[v][j];
            }
        }

        return lift[u][0]; // One step above — the LCA
    }

    // Fast binary exponentiation under mod
    int power(int base, int exp) {
        long res = 1;
        long b = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = res * b % mod;
            b = b * b % mod;
            exp >>= 1;
        }
        return (int) res;
    }
}