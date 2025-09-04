class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long contCount = 0;    // Count of continuous zeros in current sequence
        long subarrays = 0;    // Total count of zero-filled subarrays
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                contCount++;               // Increment count of continuous zeros
                subarrays += contCount;    // Add count to total (optimization)
 
            }
            else {
                contCount = 0; // Reset continuous count when non-zero is encountered
            }
        }
        
        return subarrays;
    }
}
