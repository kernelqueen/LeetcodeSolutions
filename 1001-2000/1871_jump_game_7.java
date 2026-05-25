// ===================================================
// Solution 1: DFS + Memoization (TLE on large inputs)
// Time: O(n * max), Space: O(n)
// ===================================================
class Solution {
    HashMap<Integer, List<Integer>> hmap;
    int n;
    HashSet<Integer> visited;

    public boolean canReach(String s, int min, int max) {
        n = s.length();
        visited = new HashSet<>();
        hmap = new HashMap<>();

        // Precompute adjacency list: for each '0' index, store reachable '0' indices within [i+min, i+max]
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') continue;

            hmap.put(i, new ArrayList<>());
            for (int j = i + min; j <= Math.min(i + max, n - 1); j++) {
                if (s.charAt(j) == '0')
                    hmap.get(i).add(j);
            }
        }

        return dfs(0);
    }

    boolean dfs(int ind) {
        if (ind == n - 1) return true;
        if (visited.contains(ind)) return false;

        visited.add(ind);

        for (int i = 0; i < hmap.get(ind).size(); i++) {
            int next = hmap.get(ind).get(i);
            if (!visited.contains(next)) {
                if (dfs(next)) return true;
            }
        }

        return false;
    }
}


// ===================================================
// Solution 2: Difference Array (Optimal)
// Time: O(n), Space: O(n)
// ===================================================
class Solution {
    public boolean canReach(String s, int min, int max) {
        int n = s.length();

        // Early exit: last index must be '0' to be reachable
        if (s.charAt(n - 1) == '1') return false;

        // Difference array to track how many valid positions can reach index i
        int[] arr = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            // Apply difference array increment at this index
            count += arr[i];

            // Index i is a valid jump source if it's '0' and reachable (or is the start)
            if (i == 0 || (s.charAt(i) == '0' && count > 0)) {
                // Mark the jump range [i+min, i+max] as reachable using difference array
                if (i + min < n)     arr[i + min] += 1;
                if (i + max + 1 < n) arr[i + max + 1] -= 1;
            }
        }

        // count > 0 means at least one valid path reached the last index
        return count > 0;
    }
}