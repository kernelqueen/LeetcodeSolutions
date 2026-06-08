class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        
        int n = nums.length;
        int ans[] = new int[n];
        int left=0, right =n-1;
       // O(n)
        for(int i=0; i<n; i++) {
            if(nums[i] < pivot)
                ans[left++] = nums[i];
        }
        for(int i=n-1; i>=0; i--) {
            if(nums[i] > pivot)
                ans[right--] = nums[i];
        }
        // [9,5,3,_,_,12,14]
        while(left <= right) {
            ans[left++] = pivot;
        }

        return ans;

    }
}