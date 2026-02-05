class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];

        for(int i = 0; i < n; i++) {
            // Calculate the range when moving backwards (for negative nums[i])
            int range = i - Math.abs(nums[i]);
            int index = -1;
            
            // Check if the backward movement stays within bounds
            if(range >= 0) {
                index = range;
            } else {
                // Handle wrap-around for backward movement using modulo arithmetic
                index = (n - Math.abs(nums[i] + i) % n) % n;
            }
            
            // Determine the target index based on the sign of nums[i]
            // Positive: move forward with wrap-around
            // Negative: use the calculated backward index
            ans[i] = nums[i] >= 0 ? nums[(i + nums[i]) % n] : nums[index];
        }
        
        return ans;
    }
}