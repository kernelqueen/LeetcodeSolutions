class Solution {
    int mod = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        // Build undirected adjacency list
        HashMap<Integer, List<Integer>> hmap = new HashMap<>();
        for (int[] e : edges) {
            int s = e[0], d = e[1];
            hmap.putIfAbsent(s, new ArrayList<>());
            hmap.putIfAbsent(d, new ArrayList<>());
            hmap.get(s).add(d);
            hmap.get(d).add(s);
        }

        // BFS from node 1 to find depth of the tree (level-order traversal)
        // Time: O(n), Space: O(n)
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(1);
        visited.add(1);
        int depth = -1;

        while (!q.isEmpty()) {
            depth++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.remove();
                List<Integer> children = hmap.get(curr);
                if (children == null) continue;
                for (int next : children) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
        }

        // Each edge can be odd or even, only paths where total weight is odd are counted
        // Answer = 2^(depth-1) — computed via fast exponentiation in O(log n)
        return power(2, depth - 1);
    }

    // Fast binary exponentiation under mod
    int power(int base, int exp) {
        long res = 1;
        long b = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1)             // If current bit is set, multiply result
                res = (res * b % mod) % mod;
            b = b * b % mod;                // Square the base
            exp = exp >> 1;                 // Shift to next bit
        }

        return (int) res;
    }
}