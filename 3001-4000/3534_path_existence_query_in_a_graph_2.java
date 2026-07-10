// Approach: Sort by value so an edge connects nodes within maxDiff; precompute each node's
// farthest direct reach, then use binary lifting to answer min-hops queries in O(log n).
// Time: O((n + q) log n) | Space: O(n log n)

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        // Pair each value with its original index so we can recover positions after sorting
        int sorted[][] = new int[n][2];

        for(int i=0; i<n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }

        Arrays.sort(sorted, (a,b)->Integer.compare(a[0], b[0]));

        // Map each original index to its rank in the sorted order
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(sorted[i][1], i);
        }

        // LOG = number of binary-lifting levels needed to cover n nodes
        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        int up[][] = new int[n][LOG];

        // up[i][0] = farthest sorted index reachable in one hop from i
        int r = 0;
        for (int l = 0; l < n; l++) {
            if (r < l) r = l;                        // window can't start behind l
            while (r + 1 < n && sorted[r + 1][0] - sorted[l][0] <= maxDiff) r++;
            up[l][0] = r;                            // r is now as far right as l can reach
        }

        // Binary lifting table: up[ind][j] = node reached after 2^j hops from ind
        for (int j = 1; j < LOG; j++) {
            for (int ind = 0; ind < n; ind++) {
                up[ind][j] = up[up[ind][j - 1]][j - 1];
            }
        }


        int ans[] = new int[queries.length];
        for(int ind=0; ind<queries.length; ind++) {
            int src = map.get(queries[ind][0]);
            int dest = map.get(queries[ind][1]);

            if(src > dest) {
                int temp = src;
                src = dest;
                dest = temp; // we want to travel from left to right
            }

            if(src==dest) {
                ans[ind] = 0; // same node - no hops needed
                continue;
            }

            int curr = src;
            int hops = 0;
            // Greedily take the largest jump that still lands strictly before `dest`
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < dest) {
                    curr = up[curr][j];
                    hops += (1<<j);
                }
            }

            // One more hop from curr should cover `dest`; otherwise it's unreachable
            if (up[curr][0] >= dest) {
                ans[ind] = hops + 1;
            } else {
                ans[ind] = -1; // `dest` is unreachable from `src`
            }
        }

        return ans;

    }
}