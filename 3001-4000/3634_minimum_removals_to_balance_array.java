class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = 0;
        int ans = n;
        
        while(left < n) {
            // Expand window while valid: max <= k * min
            while(right < n && nums[right] <= (long)k * nums[left]) {
                right++;
            }
            
            // Track minimum removals: total - elements kept in window
            ans = Math.min(ans, n - (right - left));
            
            // Right pointer doesn't reset due to sorted array property
            left++;
        }

        return ans;
    }
}