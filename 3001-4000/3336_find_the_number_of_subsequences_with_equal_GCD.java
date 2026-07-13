// Approach: For each element choose one of three options — add to subseq1, add to subseq2, or skip —
// while tracking each subsequence's running GCD; count arrangements where both are non-empty and GCDs match.
// Time: O(n * m^2 * log m) | Space: O(n * m^2)   (m = max value)

class Solution {
    int mod = 1_000_000_007;
    int dp[][][];
    public int subsequencePairCount(int[] nums) {
        int n = nums.length, max = 0;

        // dp is indexed by [current index][gcd of subseq1][gcd of subseq2]
        for(int num:nums)
            max = Math.max(num, max);
        dp = new int[n][max+1][max+1];
        for(int i=0; i<n; i++) {
            for(int a[] : dp[i])
                Arrays.fill(a, -1);
        }

        return helper(0,0,0,nums);
    }

    int helper(int i, int seq1, int seq2, int nums[]) {
        // Base case: valid only when both subsequences are non-empty with equal GCD
        if(i==nums.length) {
            if(seq1!=seq2 || (seq1==0 && seq2==0))
                return 0;
            else return 1;
        }

        if(dp[i][seq1][seq2]!=-1)
            return dp[i][seq1][seq2];

        // Three choices for nums[i]: extend subseq1, extend subseq2, or skip it
        long take1 = helper(i+1, gcd(nums[i], seq1), seq2, nums);
        long take2 = helper(i+1, seq1, gcd(nums[i], seq2), nums);
        long skip = helper(i+1, seq1, seq2, nums);

        long ans = (take1%mod + take2%mod + skip%mod)%mod;
        return dp[i][seq1][seq2] = (int)ans;
    }

    // gcd(x, 0) = x, so a GCD of 0 conveniently marks an empty subsequence
    int gcd(int a, int b) {
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
}