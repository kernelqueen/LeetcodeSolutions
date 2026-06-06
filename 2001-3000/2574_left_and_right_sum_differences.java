class Solution {
    public int[] leftRightDifference(int[] nums) {
        int rightsum = 0;

        int ans[] = new int[nums.length];

        for(int num : nums) {
            // O(n)
            rightsum += num;
        }

        int leftsum = 0;
        for(int i=0; i<nums.length; i++) {
            rightsum -= nums[i];
            ans[i] = Math.abs(rightsum-leftsum);
            leftsum += nums[i];
        }

        return ans;

    }
}