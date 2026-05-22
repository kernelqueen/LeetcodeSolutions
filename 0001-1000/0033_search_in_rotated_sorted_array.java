class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1;

        while(low<=high) {
            // O(logn) // O(1)
            int mid = low + (high-low)/2;
            if(nums[mid]==target)
                return mid;
            // right array sorted
            if(nums[mid] < nums[high]) {
                if(target > nums[mid] && target<=nums[high]) 
                    low = mid+1;
                else high = mid-1;
            }
            // left array sorted
            else {
                if(target>=nums[low] && target<nums[mid])
                    high = mid-1;
                else low = mid+1;
            }
        }

        return -1;
    }
}