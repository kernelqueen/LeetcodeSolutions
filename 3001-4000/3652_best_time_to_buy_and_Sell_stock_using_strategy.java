class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        // original , strategy1
        long prefixarr[] = new long[n+1];
        long prefixsell[] = new long[n+1];

        for(int i=0; i<n; i++) {
            // [0,___]
            prefixarr[i+1] = prefixarr[i] + prices[i]*strategy[i];
            prefixsell[i+1] = prefixsell[i] + prices[i];
        }
        long max = prefixarr[n];

        // traversing all n-k+1
        for(int i=k-1; i<n; i++) {
            long leftsum, ksum, rightsum = 0;
            leftsum = prefixarr[i-k+1]- prefixarr[0];
            rightsum = prefixarr[n]-prefixarr[i+1];
            ksum = prefixsell[i+1]-prefixsell[(i+1)-k/2];
            max = Math.max(max, leftsum + ksum + rightsum);
        }
        return max;
    }
}