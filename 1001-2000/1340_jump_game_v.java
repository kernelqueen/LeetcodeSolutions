class Solution {
    int[] jumps;

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        jumps = new int[n];
        Arrays.fill(jumps, -1);

        // Compute max reachable indices from every index via DFS + memoization
        // Time: O(n * d), Space: O(n)
        for (int i = 0; i < n; i++) {
            jumps[i] = dfs(i, arr, d);
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jumps[i]);
        }

        return max;
    }

    int dfs(int ind, int[] arr, int d) {
        // Return cached result if already computed
        if (jumps[ind] != -1) return jumps[ind];

        jumps[ind] = 1; // At minimum, we can stay at current index

        // Explore left within range d (stop if a taller or equal bar is hit)
        for (int i = ind - 1; i >= 0 && ind - i <= d; i--) {
            if (arr[i] < arr[ind]) {
                jumps[i] = dfs(i, arr, d);
                jumps[ind] = Math.max(jumps[ind], jumps[i] + 1);
            } else break;
        }

        // Explore right within range d (stop if a taller or equal bar is hit)
        for (int i = ind + 1; i < arr.length && i - ind <= d; i++) {
            if (arr[i] < arr[ind]) {
                jumps[i] = dfs(i, arr, d);
                jumps[ind] = Math.max(jumps[ind], jumps[i] + 1);
            } else break;
        }

        return jumps[ind];
    }
}