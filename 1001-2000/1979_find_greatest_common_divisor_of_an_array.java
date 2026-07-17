class Solution {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int num : nums) {
            // O(n)
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        return gcd(max, min); // O(log(min))
    }

    int gcd(int a, int b) {
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
}