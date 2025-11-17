class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int curr = 0; // count of number of zeroes in between two ones

        boolean prev = false; // if we encountered a one in the array 

        for(int i=0; i<nums.length; i++) {

            if(nums[i]==1) {
                if(prev && curr < k)
                    return false;
                curr = 0; // for next one in the array 
                prev = true;
            }
            else {
                if(prev)
                    curr++; 
            }
        }

        return true;
    }
}

/*

Case 1: 1001001

Case 2: 0001001

Case 3: 0010010

Case 4: 1001000



*/