class Solution {
    public int maxRotatefsumunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int fsum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            fsum += i * nums[i];
        }

        int max = fsum;

        // fsum(k) = fsum(k-1) + sum - n * nums[n-k]
        for (int k = 1; k < n; k++) {
            fsum = fsum + sum - n * nums[n - k];
            max = Math.max(max, fsum);
        }

        return max;
    }
}