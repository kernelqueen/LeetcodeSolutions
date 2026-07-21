// Approach: One trade can merge two zero-runs that are separated by a block of ones into active
// sections, so the answer is the total ones plus the largest combined length of an adjacent zero-run pair.
// Time: O(n) | Space: O(1)

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0, maxsum = 0, prevrun = -1; // prevrun = length of the previous zero-run (-1 = none yet)
        int i=0;
        while(i<n) {
            if(s.charAt(i)=='1')
            {
                ones++; // already-active section
                i++;
            } else {
                // Measure this maximal run of zeros
                int curr = 0;
                while(i<n && s.charAt(i)=='0') {
                    curr++;
                    i++;
                }

                // If a prior zero-run exists, this one is adjacent to it (separated by ones)
                if(prevrun > 0)
                    maxsum = Math.max(maxsum, prevrun + curr);
                prevrun = curr;
            }
        }

        return ones + maxsum;
    }
}