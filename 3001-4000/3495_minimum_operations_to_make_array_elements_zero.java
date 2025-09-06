class Solution {
    public long minOperations(int[][] queries) {
        
        long ans = 0L;

        for (int query[] : queries) {
            long left = query[0];
            long right = query[1];
            long prev = 1;   // starting point of current range
            long ops = 0L;   // total operations required for this query

            // Iterate through ranges defined by powers of 4:
            // [1,3], [4,15], [16,63], ... until 4^16 > 1e9
            for (long pow = 1; pow <= 16; pow++) {
                long curr = prev * 4;  // end of current range + 1

                // Find overlap of [left, right] with current range [prev, curr-1]
                long leftrange = Math.max(left, prev);
                long rightrange = Math.min(right, curr - 1);

                // If overlap exists, add contribution = length * operations needed (pow)
                if (rightrange >= leftrange) {
                    ops += (rightrange - leftrange + 1) * pow;
                }

                prev = curr;  // move to next range
            }

            // Final answer for this query (rounding up division by 2)
            ans += (ops + 1) / 2;
        }

        return ans;
    }
}


/*
why not brute force?
10^5 * 10^9 = TLE

Requirements for a number to be divisible by 4:

4⁰ → 4¹ - 1 = [1, 3] → requires 1 operation

4¹ → 4² - 1 = [4, 15] → requires 2 operations

4² → 4³ - 1 = [16, 63] → requires 3 operations

4³ → 4⁴ - 1 = [64, 255] → requires 4 operations


Maximum possible power is 16
1 <= l < r <= 10^9
4^16 > 10^9

*/
