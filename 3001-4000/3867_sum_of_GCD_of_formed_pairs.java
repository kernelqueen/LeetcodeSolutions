// Approach: Direct simulation — build prefixGCD[i] = gcd(nums[i], running max), sort it, then
// pair smallest with largest from both ends and sum each pair's gcd.
// Time: O(n log n) | Space: O(n)  
class Solution {
    public long gcdSum(int[] nums) {
        int max = -1, n = nums.length;
        int prefixGCD[] = new int[n];

        // prefixGCD[i] = gcd(nums[i], max of nums[0..i]) per the problem definition
        for(int i=0; i<n; i++) {
            max = Math.max(nums[i], max);
            prefixGCD[i] = gcd(nums[i], max);
        }
        long ans = 0;
        Arrays.sort(prefixGCD);

        // Two pointers pair smallest with largest; for odd n the middle element is skipped
        for(int i=0,j=n-1; i<j; i++,j--) {
            ans += gcd(prefixGCD[i], prefixGCD[j]);
        }

        return ans;
    }

    // Euclidean algorithm
    int gcd(int a, int b) {
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
}