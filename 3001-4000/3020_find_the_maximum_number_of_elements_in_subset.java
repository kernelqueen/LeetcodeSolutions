class Solution {
    public int maximumLength(int[] nums) {

        // Frequency map for all elements
        // Time: O(n log(maxVal)), Space: O(n)  — chain length per key is at most log(maxVal)
        HashMap<Long, Integer> hmap = new HashMap<>();
        int ones = 0;
        for (int num : nums) {
            hmap.put((long) num, hmap.getOrDefault((long) num, 0) + 1);
            if (num == 1) ones++;
        }

        // 1^k is always 1, so any odd-length subsequence of 1s is valid
        // If count is even, best odd length we can pick is ones-1
        int ans = (ones % 2 == 0) ? ones - 1 : ones;

        // 1s are already handled — remove to avoid reprocessing in chain logic
        hmap.remove(1L);

        for (long num : hmap.keySet()) {
            int count = 0;
            long curr = num;

            // Follow the squaring chain: num -> num^2 -> num^4 -> ...
            while (hmap.containsKey(curr)) {
                if (hmap.get(curr) >= 2) {
                    count += 2; // Can take a pair at this level
                } else {
                    count++;    // Only one occurrence — can end the chain here
                    break;      // Cannot continue: need pairs to keep squaring
                }
                curr = curr * curr;
            }

            // Subsequence must have odd length to satisfy the zigzag/power condition
            if (count % 2 == 0) count--;
            ans = Math.max(ans, count);
        }

        return ans;
    }
}