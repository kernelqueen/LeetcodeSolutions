class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] freq = new int[value];

        // Count frequency of normalized remainders
        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            freq[rem]++;
        }

        // Now, find the smallest number we can’t form
        int mex = 0;
        // []
        while (true) {
            int rem = mex % value;
            if (freq[rem] == 0) break;  // this remainder is exhausted
            freq[rem]--;                // use one occurrence
            mex++;
        }

        return mex;
    }
}
