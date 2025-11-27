class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        long prefix[] = new long[n+1];
        // O(n)
        for(int i=1; i<=n; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }

        long ans = Long.MIN_VALUE;
        // O(k)
        for(int i=0; i<k; i++) {

            long sum = 0;
        // O(n/k)
            for(int j=i; j+k<=n; j+=k) {
                long currsum = prefix[j+k] - prefix[j];
                sum += currsum;
                if(sum < currsum) {
                    sum = currsum;
                }
                ans = Math.max(ans, sum);
            }
        }

        return ans;
    }
}