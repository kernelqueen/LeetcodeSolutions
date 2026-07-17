// Approach: For each i, count pairs whose gcd is a multiple of i, then sweep downward subtracting
// multiples to get exact-gcd counts; prefix sum them and binary search each query's rank.
// Time: O(n + m log m + q log m) | Space: O(m)   (m = max value)

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int m = 0;
        for(int num : nums)
            m = Math.max(m, num);

        // Frequency of each value
        long count[] = new long[m+1];
        for(int num : nums) {
            count[num]++;
        }

        // gcdPairs[i] = number of pairs whose gcd is a multiple of i
        long gcdPairs[] = new long[m+1];
        for(int i=1; i<=m; i++) {
            // Total elements divisible by i (harmonic sum -> O(m log m) overall)
            for(int j=i; j<=m; j+=i) {
                gcdPairs[i] += count[j];
            }
            // Any 2 of them form a pair whose gcd is divisible by i: C(c, 2)
            gcdPairs[i] = (gcdPairs[i]*(gcdPairs[i]-1))/2;
        }

        // Sweep downward so gcdPairs[j] is already exact; subtracting multiples
        // leaves only the pairs whose gcd is exactly i
        for(int i=m; i>=1; i--) {
            for(int j=2*i; j<=m; j+=i) {
                gcdPairs[i] -= gcdPairs[j];
            }
        }

        // presum[i] = number of pairs with gcd <= i, i.e. the sorted gcd list's cumulative counts
        long presum[] = new long[m+1];
        for(int i=1; i<=m; i++) {
            presum[i] = presum[i-1] + gcdPairs[i];
        }

        int ans[] = new int[queries.length];

        for(int i=0; i<queries.length; i++) {
            long k = queries[i] + 1; // 0-indexed query -> 1-indexed rank
            // Binary search for the smallest gcd value whose cumulative count reaches k
            int left = 1, right = m;
            while(left < right) {
                int mid = left  + (right-left)/2;
                if(presum[mid]>=k)
                    right = mid;
                else left = mid+1;
            }
            ans[i] = left;
        }

        return ans;
    }
}