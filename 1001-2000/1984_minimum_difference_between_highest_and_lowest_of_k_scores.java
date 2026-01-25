class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums); // nlogn

        int min = Integer.MAX_VALUE;

        for(int i=k-1; i<nums.length; i++) {  // o(n)
            int diff = nums[i]-nums[i-k+1];
            min = Math.min(diff, min);
        }
        return min;

    }
}

/*
k = 2, 1
[1,2,4,7,9]

*/