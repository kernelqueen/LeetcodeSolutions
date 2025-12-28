class Solution {
    public int countNegatives(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        // O(m)*O(logn)
        for(int nums[] : grid) {
            if(nums[n-1] >=0 ) {
                continue;
            }
            else {
                int index = getIndex(nums);
                count += n-index;
            }
        }
        return count;

    }

    int getIndex(int nums[]) {
        int low=0, high = nums.length-1;
        while(low < high) {
            int mid = low + (high-low)/2;
            if(nums[mid] < 0) {
                high = mid;
            } else low = mid+1;
        }

        return low;
    }
}

/*
[4,3,2,-1]
[3,2,1,-1]
[1,1,-1,-2]
[-1,-1,-2,-3]
*/