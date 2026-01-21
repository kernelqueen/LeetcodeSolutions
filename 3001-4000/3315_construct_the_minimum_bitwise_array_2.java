class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();

        int ans[] = new int[n];
        for(int i = 0; i < nums.size(); i++) {


            if(nums.get(i) % 2 == 0) {
                ans[i] = -1;
                continue;
            }

            int bit = 1;
            int curr = 0;
            int num = nums.get(i);
            //// If I remove one bit from num, does adding 1 and OR’ing recreate the original number?”
            // Time complexity per number: O(log k),
            // where k is the maximum value in nums
            while ((num & bit) > 0) {

                // Clear the current set bit from 'num'
                // This creates a candidate 'curr'
                curr = (num & (~bit));

                // Move to the next higher bit
                bit = bit << 1;
            }

            ans[i] = curr;
        }

        return ans;
     }
}