
/*********************Recursive Solution************************ */
class Solution {

    public int numberOfWays(String s) {
        int seats = 0;
        for (char c : s.toCharArray()) {
            if (c == 'S') seats++;
        }

        if(seats==2)
            return 1;
        if (seats == 0 || seats % 2 != 0) return 0;

        return helper(s, 0, seats);
    }

    int helper(String s, int index, int total) {

        int n = s.length();
        int seats = 0;
        int i = index;

        // consume exactly 2 seats
        while (i < n && seats < 2) {
            if (s.charAt(i) == 'S') seats++;
            i++;
        }

        total -=2;

        if (i == n || total==0) return 1;

        int ways = 0;

        // try all divider positions (after i, i+1, i+2 ... until next S)
        int j = i;
        // allow immediate divider (0 plants)

        ways += helper(s, i, total);
        while (j < n && s.charAt(j) == 'P') {
            ways += helper(s, j, total);
            j++;
        }

        
        // System.out.println(ways);

        return ways;
}

}

/******************Recursive with Memoization ***************** */
class Solution {

    int mod = 1_000_000_007;
    Integer[] dp;

    public int numberOfWays(String s) {

        int seats = 0;
        for (char c : s.toCharArray()) {
            if (c == 'S') seats++;
        }

        if (seats == 0 || seats % 2 != 0) return 0;

        int n = s.length();
        dp = new Integer[n];

        return helper(s, 0, seats);
    }

    int helper(String s, int index, int total) {

        if (index >= s.length()) return 1;

        if (dp[index] != null)
            return dp[index];

        int n = s.length();
        int seats = 0;
        int i = index;

        // consume exactly 2 seats
        while (i < n && seats < 2) {
            if (s.charAt(i) == 'S') seats++;
            i++;
        }

        if (seats < 2) return 0;

        total -= 2;
        if (total == 0) return 1;

        long ways = 0;

        // immediate divider
        ways += helper(s, i, total);

        // dividers over plants
        int j = i;
        while (j < n && s.charAt(j) == 'P') {
            ways = (ways + helper(s, j, total)) % mod;
            j++;
        }

        return dp[index] = (int) ways;
    }

}
/**************************Iterative DP Solution************************ */
class Solution {

    int mod = 1_000_000_007;

    public int numberOfWays(String s) {

        int seats = 0;
        for (char c : s.toCharArray()) {
            if (c == 'S') seats++;
        }

        if (seats == 0 || seats % 2 != 0) return 0;

        int n = s.length();
        long[] dp = new long[n + 1]; // dp[i] = ways from index i to end
        dp[n] = 1; // base case: beyond last index = 1 way

        for (int index = n - 1; index >= 0; index--) {
            int i = index;
            int seatCount = 0;

            // consume exactly 2 seats
            while (i < n && seatCount < 2) {
                if (s.charAt(i) == 'S') seatCount++;
                i++;
            }

            if (seatCount < 2) {
                dp[index] = 0;
                continue;
            }

            // add ways for immediate divider
            long ways = dp[i];

            // add ways for dividers over plants
            int j = i;
            while (j < n && s.charAt(j) == 'P') {
                ways = (ways + dp[j + 1]) % mod;
                j++;
            }

            dp[index] = ways;
        }

        return (int) dp[0];
    }
}



/*****************************Optimal Solution************************ */
class Solution {
    public int numberOfWays(String corridor) {
        int mod = 1_000_000_007;
        long ans = 1;
        int prev = 0;
        int seats = 0;

        for(int i=0; i<corridor.length(); i++) {
            char ch = corridor.charAt(i);
            if(ch=='S') {
                seats++;
                if(seats > 2 && seats%2==1) {
                    ans = (ans * (i-prev))%mod;
                }
                prev = i;
            }
        }

        return seats > 1 && seats%2==0 ? (int)ans : 0;
    }
}