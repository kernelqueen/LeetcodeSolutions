class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1;
        for(int i=n-1; i>=0; i--) {
            int curr = (digits[i] + carry);
            // 8,9,9 = [9,9,9]
            // [9,9,9] 
            if(curr < 10) {
                digits[i] = curr;
                return digits;
            }
            digits[i] = curr%10;
        }
        int ans[] = new int[n+1];
        ans[0] = 1;
        return ans;
    }
}