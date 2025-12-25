class Solution {
    public long maximumHappinessSum(int[] nums, int k) {
        Arrays.sort(nums);

        long sum = 0;
        int i = nums.length-1;
        int subval = 0;
        // O(nlogn+k)
        while(k > 0) {
            if(nums[i]-subval <=0 ) {
                break;
            }
            sum += (nums[i]-subval);
            k--;
            i--;
            subval++;
        }

        return sum;
    }
}
