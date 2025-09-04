class Solution {
    public int longestSubarray(int[] nums) {
        int countzero = 0;
        int maxlen = 0;
        int start = 0;

        for(int i=0; i<nums.length; i++) {
            if(nums[i]==0) {
                countzero++;
            }
            while(countzero > 1) {
                if(nums[start]==0) {
                    countzero--;
                }
                start++;
            }
            maxlen = Math.max(maxlen, i-start);
        }

        return maxlen;
    }
}
