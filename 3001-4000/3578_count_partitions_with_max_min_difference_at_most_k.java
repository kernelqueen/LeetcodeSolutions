class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int mod = 1_000_000_007;

        // dp[n] = number of ways to partition array [0..n-1]
        int[] dp = new int[n + 1];
        dp[0] = 1;                 

        long sum = 1; // initially only dp[0]

        Deque<Integer> minq = new ArrayDeque<>();
        Deque<Integer> maxq = new ArrayDeque<>();

        int i = 0; // left pointer of the window
        for (int j = 0; j < n; j++) {
            // add nums[j] to max deque
            while (!maxq.isEmpty() && nums[j] > nums[maxq.peekLast()])
                maxq.pollLast();
            maxq.addLast(j);

            // add nums[j] to min deque
            while (!minq.isEmpty() && nums[j] < nums[minq.peekLast()])
                minq.pollLast();
            minq.addLast(j);

            // shrink left until window [i..j] is valid (max - min <= k)
            while (nums[maxq.peekFirst()] - nums[minq.peekFirst()] > k) {
                // remove dp[i] contribution from sum
                sum = (sum - dp[i] + mod) % mod;
                i++;
                
                if (!minq.isEmpty() && minq.peekFirst() < i) minq.pollFirst();
                if (!maxq.isEmpty() && maxq.peekFirst() < i) maxq.pollFirst();
            }

            // now [i..j] is valid
            dp[j + 1] = (int) sum;

            sum = (sum + dp[j + 1]) % mod;
        }


        return dp[n];
    }
}
