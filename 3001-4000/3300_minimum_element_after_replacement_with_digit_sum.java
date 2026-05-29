class Solution {
    public int minElement(int[] nums) {
        
        int ans = Integer.MAX_VALUE;

        for(int num : nums) {
            // O(n*d)
            int sum = getDigitSum(num);
            ans = Math.min(sum, ans);
        }
        return ans;

    }

    int getDigitSum(int num) {
        int sum = 0;
        while(num > 0) {
            sum += num%10;
            num = num/10;
        }
        return sum;
    }
}