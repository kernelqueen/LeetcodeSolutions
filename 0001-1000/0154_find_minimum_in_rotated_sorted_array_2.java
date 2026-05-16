class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length-1;

        while(low < high) {
            int mid = low + (high-low)/2;
            if(nums[mid] > nums[high])
                low = mid+1;
            else high = high-1; // We are keeping entire array in search space
        }

        return nums[low];
    }
}