class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int len = 2*n + 2;
        // O(n)
        long[] countAtSum = new long[len];
        long[] cumulativeCount = new long[len];

        int idx = n+1;
        countAtSum[idx] = 1;
        cumulativeCount[idx] = 1;

        long ans = 0;

        for(int num : nums) {
            // O(n)
            if(num==target)
                idx++;
            else idx--;

            countAtSum[idx]++;
            ans += cumulativeCount[idx-1];
            cumulativeCount[idx] = cumulativeCount[idx-1] + countAtSum[idx];
        }

        return ans;
    }
}