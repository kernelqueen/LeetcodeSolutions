class Solution {
    Map<String, long[]> map;

    // Time: O(D * 11 * 11 * 2 * 2) = O(D), Space: O(D)
    // where D = number of digits in num2

    public long totalWaviness(long num1, long num2) {
        // Count waviness in [num1, num2] using prefix subtraction
        return getWaves(num2) - getWaves(num1 - 1);
    }

    // Returns total waviness for all numbers in [0, num]
    long getWaves(long num) {
        map = new HashMap<>();
        String s = Long.toString(num);

        // Numbers with fewer than 3 digits can't have a wave peak/valley
        if (s.length() < 3) return 0;

        long[] ans = helper(s, 0, true, -1, -1, true);
        return ans[1]; // ans[0] = count of valid numbers, ans[1] = total waviness
    }

    // Digit DP helper
    // Returns long[]{count of valid numbers, total waviness} for the remaining suffix
    // idx          - current digit position
    // isBound      - whether current prefix is still tight to the upper bound
    // twobefore    - digit two positions back (-1 if not yet set)
    // onebefore    - digit one position back (-1 if not yet set)
    // leadingZero  - whether all digits so far have been zero (to skip leading zero numbers)
    long[] helper(String s, int idx, boolean isBound,
                  int twobefore, int onebefore, boolean leadingZero) {

        // Base case: full number formed
        if (idx == s.length()) {
            // Leading zero means the number was effectively empty/zero — not valid
            return leadingZero ? new long[]{0, 0} : new long[]{1, 0};
        }

        String key = idx + "#" + isBound + "#" + twobefore + "#" + onebefore + "#" + leadingZero;
        if (map.containsKey(key)) return map.get(key);

        int upper = isBound ? (s.charAt(idx) - '0') : 9;
        long totalCount = 0L, totalWaves = 0L;

        for (int i = 0; i <= upper; i++) {
            long wave = 0;
            int nextTwoBefore, nextOneBefore;

            if (leadingZero) {
                // Still in leading zero phase — don't count digits as part of the number yet
                nextTwoBefore = -1;
                nextOneBefore = (i == 0) ? -1 : i;
            } else {
                nextTwoBefore = onebefore;
                nextOneBefore = i;

                // Check if onebefore forms a wave peak or valley with its neighbors
                if (twobefore >= 0 && onebefore >= 0) {
                    if ((onebefore > twobefore && onebefore > i)   // peak
                    ||  (onebefore < twobefore && onebefore < i))  // valley
                        wave = 1;
                }
            }

            boolean nextBound = isBound && (i == upper);
            boolean nextLeadingZero = leadingZero && (i == 0);

            long[] sub = helper(s, idx + 1, nextBound, nextTwoBefore, nextOneBefore, nextLeadingZero);

            totalCount += sub[0];
            // Each of the sub[0] numbers contributes the current wave, plus their own internal waves
            totalWaves += sub[1] + sub[0] * wave;
        }

        long[] result = {totalCount, totalWaves};
        map.put(key, result);
        return result;
    }
}