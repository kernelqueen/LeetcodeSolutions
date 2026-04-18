class Solution {
    public int mirrorDistance(int n) {
        int rev = getReverse(n);
        return Math.abs(n-rev);
    }

    int getReverse(int num) {
        int ans = 0;
        while(num > 0) {
            ans = ans*10 + num%10;
            num = num/10;
        }
        return ans;
    }
}