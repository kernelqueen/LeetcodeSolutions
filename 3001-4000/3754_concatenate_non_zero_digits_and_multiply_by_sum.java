class Solution {
    public long sumAndMultiply(int n) {
        String s = Integer.toString(n);

        // O(logn)

        long ans = 0, sum = 0;

        for(int i=0; i<s.length(); i++) {
            int digit = s.charAt(i)-'0';
            if(digit!=0) {
                sum += digit;
                ans = ans*10 + digit;
            }
        }

        return sum*ans;
    }
}
