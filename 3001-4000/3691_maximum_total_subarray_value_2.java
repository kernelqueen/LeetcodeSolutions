class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        // Segment tree supports O(log n) min/max range queries.
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++)
            st.insert(i, nums[i]);

        // [rangeValue, left, right]
        // rangeValue = max(nums[l..r]) - min(nums[l..r])
        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        int[] ansVal = st.query(0, n - 1);
        pq.offer(new long[]{ansVal[1] - ansVal[0], 0, n - 1});

        // Avoid processing the same subarray more than once.
        Set<String> visited = new HashSet<>();
        visited.add(0 + "#" + (n - 1));

        long ans = 0;

        while (k > 0 && !pq.isEmpty()) {
            long[] curr = pq.poll();

            ans += curr[0];
            k--;

            int l = (int) curr[1];
            int r = (int) curr[2];

            // Generate the two neighboring subarrays:
            // [l + 1, r] and [l, r - 1]
            if (l + 1 <= r && !visited.contains((l + 1) + "#" + r)) {
                int[] val = st.query(l + 1, r);

                visited.add((l + 1) + "#" + r);
                pq.offer(new long[]{val[1] - val[0], l + 1, r});
            }

            if (l <= r - 1 && !visited.contains(l + "#" + (r - 1))) {
                int[] val = st.query(l, r - 1);

                visited.add(l + "#" + (r - 1));
                pq.offer(new long[]{val[1] - val[0], l, r - 1});
            }
        }

        return ans;
    }
}

/*
 * Segment Tree
 * Stores min and max for every range.
 *
 * Build: O(n log n) via point inserts
 * Query: O(log n)
 *
 * Overall:
 * O(n log n + k log k log n)
 */
class SegmentTree {
    int n;
    int[] maxValues, minValues;

    SegmentTree(int n) {
        this.n = n;
        maxValues = new int[4 * n];
        minValues = new int[4 * n];
    }

    void insert(int idx, int val) {
        insert(1, 0, n - 1, idx, val);
    }

    void insert(int node, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            maxValues[node] = val;
            minValues[node] = val;
            return;
        }

        int mid = lo + (hi - lo) / 2;

        if (idx <= mid) {
            insert(2 * node, lo, mid, idx, val);
        } else {
            insert(2 * node + 1, mid + 1, hi, idx, val);
        }

        maxValues[node] =
            Math.max(maxValues[2 * node], maxValues[2 * node + 1]);

        minValues[node] =
            Math.min(minValues[2 * node], minValues[2 * node + 1]);
    }

    // Returns [min, max] in range [l, r].
    int[] query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }

    int[] query(int node, int lo, int hi, int l, int r) {
        // No overlap.
        if (r < lo || l > hi)
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        // Complete overlap.
        if (l <= lo && hi <= r)
            return new int[]{minValues[node], maxValues[node]};

        int mid = lo + (hi - lo) / 2;

        int[] left = query(2 * node, lo, mid, l, r);
        int[] right = query(2 * node + 1, mid + 1, hi, l, r);

        return new int[]{
            Math.min(left[0], right[0]),
            Math.max(left[1], right[1])
        };
    }
}