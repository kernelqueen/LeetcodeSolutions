class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        
        // Initialize doubly linked list pointers
        int[] prev = new int[n];
        int[] next = new int[n];
        Arrays.fill(prev, -1);
        Arrays.fill(next, -1);
        
        // Store current values (will be updated as pairs merge)
        long[] array = new long[n];
        array[n - 1] = (long) nums[n - 1];
        
        // Build linked list structure
        for (int i = 0; i < n - 1; i++) {
            array[i] = (long) nums[i];
            next[i] = i + 1;
            prev[i + 1] = i;
        }
        
        // TreeSet to track pairs by sum (greedy selection)
        TreeSet<long[]> pairSet = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });
        
        // Count gaps where array[i] > array[i+1]
        int gapCount = 0;
        for (int i = 0; i < n - 1; i++) {
            pairSet.add(new long[]{array[i] + array[i + 1], (long) i});
            if (array[i] > array[i + 1]) {
                gapCount++;
            }
        }
        
        int operationCount = 0;
        
        // Greedily merge pairs until all gaps are resolved
        while (gapCount > 0) {
            long[] minPair = pairSet.first();
            pairSet.remove(minPair);
            
            int i = (int) minPair[1];
            int j = next[i];
            operationCount++;
            
            // Update gap count before merge
            if (j != -1 && array[i] > array[j]) {
                gapCount--;
            }
            if (prev[i] != -1 && array[prev[i]] > array[i]) {
                gapCount--;
            }
            if (j != -1 && next[j] != -1 && array[j] > array[next[j]]) {
                gapCount--;
            }
            
            // Remove affected pairs from TreeSet
            if (prev[i] != -1) {
                pairSet.remove(new long[]{array[prev[i]] + array[i], (long) prev[i]});
            }
            if (j != -1 && next[j] != -1) {
                pairSet.remove(new long[]{array[j] + array[next[j]], (long) j});
            }
            
            // Merge: combine i and j
            array[i] += array[j];
            
            // Update linked list
            int nextJ = next[j];
            next[i] = nextJ;
            if (nextJ != -1) {
                prev[nextJ] = i;
            }
            next[j] = prev[j] = -1;
            
            // Update gap count after merge
            if (prev[i] != -1 && array[prev[i]] > array[i]) {
                gapCount++;
            }
            if (next[i] != -1 && array[i] > array[next[i]]) {
                gapCount++;
            }
            
            // Add new pairs to TreeSet
            if (prev[i] != -1) {
                pairSet.add(new long[]{array[prev[i]] + array[i], prev[i]});
            }
            if (next[i] != -1) {
                pairSet.add(new long[]{array[i] + array[next[i]], i});
            }
        }
        
        return operationCount;
    }
}
