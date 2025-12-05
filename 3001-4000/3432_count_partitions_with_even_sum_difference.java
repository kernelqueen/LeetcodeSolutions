class Solution {
    public int countPartitions(int[] nums) {
        
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        int left=0, right=sum;
        int ans = 0;

        for(int i=0; i<nums.length-1; i++) {
            left += nums[i];
            right -= nums[i];
            if(Math.abs(left-right)%2==0) {
                ans++;
            }
        }
        return ans;
    }
}

