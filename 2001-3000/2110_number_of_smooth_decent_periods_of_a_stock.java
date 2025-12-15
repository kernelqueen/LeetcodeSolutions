class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long ans = 0;

        int i=0;

        while( i < n) {
            int len = 1;

            while(i+1<n && prices[i+1]==prices[i]-1) {
                i++;
                len++;
            }

            ans += (long)len * (len+1)/2;
            i++;
        }
        return ans;
    }
}