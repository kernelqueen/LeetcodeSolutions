/*Recursive Approach - TLE */
class Solution {
    public long maximumTotalDamage(int[] power) {

        // Build frequency map -> power : count
        Map<Integer, Long> count = new HashMap<>();
        for (int p : power) {
            count.put(p, count.getOrDefault(p, 0L) + 1);
        }

        // Extract unique powers and sort them
        List<Integer> keys = new ArrayList<>(count.keySet());
        Collections.sort(keys); // O(n log n)

        // Recursive (without memoization)
        return helper(keys, count, keys.size() - 1);
    }

    private long helper(List<Integer> keys, Map<Integer, Long> count, int i) {
        // Base case: if index is out of range
        if (i < 0) return 0;

        // Option 1: skip current power
        long skip = helper(keys, count, i - 1);

        // Option 2: take current power
        int curr = keys.get(i);
        long take = count.get(curr) * curr;

        // Find previous non-conflicting index (difference >= 3)
        int j = i - 1;
        while (j >= 0 && keys.get(j) >= curr - 2) j--;

        // Add best value up to j
        take += helper(keys, count, j);

        // Return the best of taking or skipping
        return Math.max(take, skip);
    }
}


/*Recursive approach with Memoization */
class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> count = new HashMap<>();
        // Build frequency map of power → count
        // Example: [1,1,1,1,4,5,6,6,8]
        // count = {1=4, 4=1, 5=1, 6=2, 8=1}
        for (int p : power) {
            count.put(p, count.getOrDefault(p, 0L) + 1);
        }

        // Extract unique powers and sort them in ascending order
        // Example: [1,4,5,6,8]
        List<Integer> keys = new ArrayList<>(count.keySet());
        Collections.sort(keys);

        // Memoization array to store previously computed results
        Long[] dp = new Long[keys.size()];

        // Start recursion from the last index
        return helper(keys, count, keys.size() - 1, dp);
    }

    private long helper(List<Integer> keys, Map<Integer, Long> count, int i, Long[] dp) {
        // Base case: no powers left
        if (i < 0) return 0;

        // Return cached result if already computed
        if (dp[i] != null) return dp[i];

        // Option 1: Skip current power
        long skip = helper(keys, count, i - 1, dp);

        // Option 2: Take current power
        int curr = keys.get(i); // Current power value
        long take = count.get(curr) * curr; // Damage contributed by all occurrences of this power

        // Find previous index where power difference >= 3 (non-conflicting)
        int j = i - 1;
        while (j >= 0 && keys.get(j) >= curr - 2) j--;

        // Add the best possible damage from previous valid powers
        take += helper(keys, count, j, dp);

        // Store and return the maximum of taking or skipping
        return dp[i] = Math.max(take, skip);
    }
}


/*Iterative Approach */
class Solution {
    public long maximumTotalDamage(int[] power) {

        // Build frequency map -> power : count
        Map<Integer, Long> count = new HashMap<>();
        for (int p : power) {
            count.put(p, count.getOrDefault(p, 0L) + 1);
        }

        // Extract unique power values and sort them
        List<Integer> keys = new ArrayList<>(count.keySet()); 
        Collections.sort(keys); // O(n log n)

        int n = keys.size();
        long[] dp = new long[n];

        // Base case: only one power type
        dp[0] = count.get(keys.get(0)) * keys.get(0);

        // Build dp iteratively for each unique power
        for (int i = 1; i < n; i++) {
            // Option 1: skip current power (carry previous max)
            long skip = dp[i - 1];

            // Option 2: take current power
            int curr = keys.get(i);
            long take = count.get(curr) * curr;

            // Find previous non-conflicting power (diff >= 3)
            int j = i - 1;
            while (j >= 0 && keys.get(j) >= curr - 2) j--;

            // If found, add its dp value
            if (j >= 0) 
                take += dp[j];

            // Choose maximum of take or skip
            dp[i] = Math.max(take, skip);
        }

        // Final maximum total damage
        return dp[n - 1];
    }
}
