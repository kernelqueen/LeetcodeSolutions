class Solution {
    public int minimumCost(int[] nums) {
        int first = nums[0];
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for(int i=1; i<nums.length; i++) { // O(n)
            if(nums[i] <=min1 || nums[i]<=min2) {
                // 10,2,4,3,1
                // min1 = MAX, min2 = MAX
                // min1 = 1
                // min2 = 2
                min2 = Math.min(min2, min1);
                min1 = nums[i];
            }
        }

        return first + min1 + min2;
    }
}