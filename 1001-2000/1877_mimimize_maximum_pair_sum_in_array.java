class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums); // Nlogn, O(1)
        int max = 0;
        int i=0, j=nums.length-1;
        while( i < j) {
            max = Math.max(max, nums[i]+nums[j]);
            i++; j--; 
        }
        return max;
    }
}
/*

Sorted Numbers:

[a,b,c,d...,m,n]

a < b < c < d < ... < m < n

Let's Pair the largest number --- 

Let's assume (a+n) is not smallest.

But can any other number paired with n give us smallest?
NO

Hence we need to pair like this - 

Largest with smallest
2nd Largest with 2nd Smallest
and so on...

This will give us optimal answer.
*/