class Solution {
    public int minOperations(int[] nums) {
        
        int curr = nums[0];
        for(int num : nums) {
            if(num!=curr)
                return 1;
        }
        return 0;

    }
}
