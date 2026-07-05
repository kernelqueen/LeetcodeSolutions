class Solution {
    int mod = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size(), n = board.get(0).length();

        // dp[i][j]    = maximum score reachable from (i,j) to 'S' (bottom-right)
        // count[i][j] = number of paths achieving that maximum score
        // Time: O(m * n), Space: O(m * n)
        long[][] dp    = new long[m][n];
        long[][] count = new long[m][n];

        for (long[] row : dp) Arrays.fill(row, Long.MIN_VALUE);

        // Base case: 'S' is the starting cell (bottom-right), score 0, 1 way
        dp[m - 1][n - 1]    = 0;
        count[m - 1][n - 1] = 1;

        // Traverse bottom-right to top-left
        // Each cell looks at its right, down, and diagonal-down neighbors (already filled)
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char ch = board.get(i).charAt(j);

                // 'S' is handled as base case; 'X' is blocked
                if (ch == 'S' || ch == 'X') continue;

                long best = Long.MIN_VALUE, ways = 0;

                // Check all valid successors (right, down, diagonal)
                for (int[] d : dirs) {
                    int pi = i + d[0], pj = j + d[1];
                    if (pi < m && pj < n && dp[pi][pj] != Long.MIN_VALUE) {
                        if (dp[pi][pj] > best) {
                            best = dp[pi][pj];
                            ways = count[pi][pj];
                        } else if (dp[pi][pj] == best) {
                            ways = (ways + count[pi][pj]) % mod;
                        }
                    }
                }

                if (best == Long.MIN_VALUE) continue; // No reachable path from here

                // 'E' is the end cell (top-left), contributes 0 to score
                int num = (ch == 'E') ? 0 : ch - '0';
                dp[i][j]    = best + num;
                count[i][j] = ways;
            }
        }

        int[] ans = new int[2];
        if (dp[0][0] != Long.MIN_VALUE) {
            ans[0] = (int) dp[0][0];
            ans[1] = (int) count[0][0];
        }

        return ans;
    }
}