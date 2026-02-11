class SegmentTree {
    int n;
    int[] minBalance;   // minimum balance value in this segment
    int[] maxBalance;   // maximum balance value in this segment
    int[] lazyval;      // pending additions not yet propagated to children

    public SegmentTree(int n) {
        this.n = n;
        // Array size is 4*n to accommodate complete binary tree structure
        // Node indexing: parent at i, left child at 2*i, right child at 2*i+1
        minBalance = new int[4 * n];
        maxBalance = new int[4 * n];
        lazyval = new int[4 * n];
    }

    /**
     * Push down pending lazy values from node to its children
     * This applies deferred updates before we read/modify this node
     */
    private void push(int node, int start, int end) {
        if (lazyval[node] != 0) {
            // Apply the pending update to current node's min/max
            minBalance[node] += lazyval[node];
            maxBalance[node] += lazyval[node];

            // If not a leaf, propagate the lazy value to children
            // (defer the actual work to when we visit those children)
            if (start != end) {
                lazyval[node * 2]     += lazyval[node];  // left child
                lazyval[node * 2 + 1] += lazyval[node];  // right child
            }

            // Clear the lazy value at this node (it's been handled)
            lazyval[node] = 0;
        }
    }

    /**
     * Add 'val' to all elements in range [l, r]
     * node: current node index in segment tree
     * [start, end]: range represented by current node
     * [l, r]: range we want to update
     * val: value to add
     */
    public void update(int node, int start, int end,
                            int l, int r, int val) {

        // Apply any pending updates at this node first
        push(node, start, end);

        // Case 1: No overlap - current segment [start,end] doesn't intersect [l,r]
        if (start > r || end < l) return;

        // Case 2: Complete overlap - [start,end] is fully inside [l,r]
        // Mark this node as lazy and return (don't recurse further)
        if (l <= start && end <= r) {
            lazyval[node] += val;
            push(node, start, end);  // immediately apply to this node
            return;
        }

        // Case 3: Partial overlap - need to update both children
        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, val);        // update left child
        update(node * 2 + 1, mid + 1, end, l, r, val);  // update right child

        // After updating children, recompute this node's min/max from children
        // This maintains the segment tree property
        minBalance[node] = Math.min(minBalance[node * 2], minBalance[node * 2 + 1]);
        maxBalance[node] = Math.max(maxBalance[node * 2], maxBalance[node * 2 + 1]);
    }

    /**
     * Find the leftmost index where balance == 0
     * Returns -1 if no such index exists
     */
    public int getLeftMost(int node, int start, int end) {
        // Apply any pending updates before checking values
        push(node, start, end);
        
        // Optimization: if entire segment has no zeros, return early
        // - If min > 0, all values are positive (no zero possible)
        // - If max < 0, all values are negative (no zero possible)
        if (minBalance[node] > 0 || maxBalance[node] < 0) {
            return -1;
        }

        // Base case: we've reached a leaf node
        if (start == end) {
            return minBalance[node] == 0 ? start : -1;
        }

        // Recursive case: search children
        int mid = (start + end) / 2;

        // Always search left subtree first (we want leftmost zero)
        int left = getLeftMost(node * 2, start, mid);
        if (left != -1) return left;  // found in left, return immediately

        // Only search right if not found in left
        return getLeftMost(node * 2 + 1, mid + 1, end);
    }
}

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;

        // Track the last occurrence index of each distinct number
        HashMap<Integer, Integer> prevIndex = new HashMap<>();

        // Segment tree to maintain balance at each position
        // balance[i] = (count of distinct evens from i to end) - (count of distinct odds from i to end)
        SegmentTree st = new SegmentTree(n);

        int maxlen = 0;

        // Process each element left to right
        for(int i = 0; i < n; i++) {
            // Even numbers contribute +1, odd numbers contribute -1
            int val = (nums[i] % 2 == 0) ? 1 : -1;
            
            // If this number appeared before, it's no longer "distinct" at that position
            // Remove its contribution from indices [0, prevIndex]
            if(prevIndex.containsKey(nums[i])) {
                int prev = prevIndex.get(nums[i]);
                st.update(1, 0, n - 1, 0, prev, -val);  // subtract the old contribution
            }
            
            // Add this number's contribution to all indices [0, i]
            // (it's now part of the "distinct" set for subarrays starting at 0..i)
            st.update(1, 0, n - 1, 0, i, val);
            
            // Update the last seen index for this number
            prevIndex.put(nums[i], i);

            // Find leftmost index where balance = 0
            // This means equal distinct evens and odds in subarray [left..i]
            int left = st.getLeftMost(1, 0, n - 1);
            if(left != -1 && left <= i) {
                maxlen = Math.max(maxlen, i - left + 1);
            }
        }

        return maxlen;
    }
}