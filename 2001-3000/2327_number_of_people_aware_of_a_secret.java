class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = 1000000007;
        int dp[] = new int[n+1];
        // dp[day] -- number of people who got to know the secret on the this day

        int curr = 0; // number of people who shares common secret

        dp[1] = 1;

        for(int day=2; day<=n; day++) {
            if(day-delay > 0) {
                curr = (curr + dp[day-delay])%mod;
            }
            // people who will forget secret
            if(day-forget > 0) {
                curr = (curr - dp[day-forget] + mod )%mod;
            }

            dp[day] = curr;
        }

        // accumulate the number of people who know the secrey on nth day

        int total = 0;

        for(int day=n-forget+1; day<=n; day++) {
            if(day > 0) {
                total = (total + dp[day]) % mod;
            }
        }

        return total;

    }
}

/*


                A (Day 1, delay =2, forgets=4)
                   /             \
                  /               \
         (Day 3) B               C (Day 4)
                /   \                 \
               /     \                 \
       (Day 5)D   (Day 6)E      (Day 6)F







Day:   1  2  3  4  5  6
dp[]:  1  0  1  1  1  2






In math, -3 mod 7 should be 4.

In Java, -3 % 7 = -3.

So the +mod trick is just a programmer’s hack to force the language to give the correct mathematical result.

(-1 + 7) % 7 = 6
-1%7 = -1
[0,6]

   -7   -6    -5    -4     -3    -2     -1    0    1     2      3     4     5    
    |     |     |     |     |     |     |     |     |     |     |     |     |    
  (0)   (1)   (2)   (3)   (4)   (5)   (6)   (0)   (1)   (2)   (3)   (4)   (5)
*/
