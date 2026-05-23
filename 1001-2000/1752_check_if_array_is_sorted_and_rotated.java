class Solution {
    public boolean check(int[] nums) {
        int index = -1;

        // O(n)

        for(int i=1; i<nums.length; i++) {
            if(nums[i] < nums[i-1]) {
                if(index==-1)
                    index = i;
                else return false; // can not have two breaks
            }
        }
        // [1,2,3,4,5]
        if(index==-1)
            return true;
        if(nums[0] >= nums[nums.length-1])
            return true;
        else return false;
        
    }
}

