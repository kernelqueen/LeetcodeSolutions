/* -------------------------------- HashMap Approach -------------------------------- */
class Solution {
    public int specialTriplets(int[] nums) {

        // one thing - counting arrays
        // leftcount[] = new int[100001];
        // rightcount[]
        
        HashMap<Integer, Integer> leftcount = new HashMap<>();
        HashMap<Integer, Integer> rightcount = new HashMap<>();

        long ans = 0;
        int mod = 1000000007;

        for(int num : nums) {
            rightcount.put(num, rightcount.getOrDefault(num, 0)+1);
        }

        for(int ind=0; ind<nums.length; ind++) {

            int num = nums[ind];
            rightcount.put(num, rightcount.get(num)-1);
            if(rightcount.get(num)==0)
                rightcount.remove(num);
            int left = leftcount.containsKey(num*2) ?  leftcount.get(num*2) : 0;
            int right = rightcount.containsKey(num*2) ? rightcount.get(num*2) : 0;

            ans = (ans%mod + ((long)left*right)%mod)%mod;

            leftcount.put(num, leftcount.getOrDefault(num,0)+1);

        }

        return (int)ans;

    }
}
/*
nums = [8,4,2,8,4]

Triplet 1: [8,4,8]
Triplet 2: [4,2,4]

*/



/*-----------------------------Counting Array Approach ----------------------------- */

class Solution {

    int[] leftcount = new int[100001];
    int[] rightcount = new int[100001];
    private static final int MOD = 1_000_000_007;

    public int specialTriplets(int[] nums) {

        // Count frequencies of all numbers initially
        for (int num : nums) {
            rightcount[num]++;
        }

        int ans = 0;

        for (int num : nums) {

            // This num moves from "remaining" to "prefix"
            rightcount[num]--;

            int doubled = num * 2;

            // Count pairs of (i, j, k) where nums[j] = doubled
            if (doubled < leftcount.length) {
                long ways = ((long) leftcount[doubled] * (long)rightcount[doubled])%MOD;
                ans = (ans%MOD + (int)ways%MOD) % MOD;
            }

            leftcount[num]++;
        }

        return (int)ans;
    }
}
