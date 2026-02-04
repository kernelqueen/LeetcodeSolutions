class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long presum[] = new long[n+1];

        // Build prefix sum array - O(n)
        for(int i=1; i<=n; i++) {
            presum[i] = presum[i-1] + nums[i-1];
        }

        long maxsum = Long.MIN_VALUE;
        int i = 1;

        // Outer loop - O(n^2) worst case
        while(i < n-2) {
            int left = i-1;

            // First increasing part: nums[left...pIndex]
            while(i < n && nums[i] > nums[i-1])
                i++;
            if(i == left+1) {
                i++;
                continue;
            }
            int pIndex = i-1;

            // Decreasing part: nums[pIndex...qIndex]
            while(i < n && nums[i] < nums[i-1]) 
                i++;
            if(i == pIndex+1) {
                i++;
                continue;
            }
            int qIndex = i-1;

            // Second increasing part: nums[qIndex...right]
            while(i < n && nums[i] > nums[i-1])
                i++;
            if(i == qIndex+1) {
                i++;
                continue;
            }
            int right = i-1;

            // Find max sum of trionic subarray [left...right]
            // maxprefixsum: max of presum in range [qIndex+1...right+1]
            // minprefixsum: min of presum in range [left...pIndex-1]
            // Difference gives max sum of any subarray ending in [qIndex...right] and starting in [left...pIndex]
            long maxprefixsum = Long.MIN_VALUE;
            long minprefixsum = Long.MAX_VALUE;
            
            for(int j = left; j < pIndex; j++) {
                minprefixsum = Math.min(minprefixsum, presum[j]); 
            }
            
            for(int j = qIndex+1; j <= right; j++) {
                maxprefixsum = Math.max(maxprefixsum, presum[j+1]); 
            }
            
            maxsum = Math.max(maxsum, maxprefixsum - minprefixsum);
            i = qIndex + 1;
        }

        return maxsum;
    }
}