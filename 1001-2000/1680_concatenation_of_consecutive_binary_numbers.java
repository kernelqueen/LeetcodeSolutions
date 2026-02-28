class Solution {
    int mod = 1_000_000_007;
    public int concatenatedBinary(int n) {
        long ans = 0;
        int shift = 0;
        // O(n)
        for(int i=1; i<=n; i++) {
            // if power of 2
            // O(32)
            if(Integer.bitCount(i)==1) {
                shift++;
            }
            ans = ((ans << shift) + i)%mod;
        }
        return (int)ans;
    }
}