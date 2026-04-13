class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int minDiff = Integer.MAX_VALUE;
        // O(n), O(1)
        for(int i=0; i<nums.length; i++){
            int diff = Math.abs(start-i);
            if(nums[i]==target && diff<minDiff)
                minDiff = diff;
        }
        return minDiff;
    }
}