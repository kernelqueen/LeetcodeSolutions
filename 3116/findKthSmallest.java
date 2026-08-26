class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) k * getMin(coins);

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long total = 0;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = getLCM(lcm, coins[i]);

                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) {
                continue;
            }

            long multiples = x / lcm;

            if (bits % 2 == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    private long getGCD(long a, long b) {

        while (b != 0) {

            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private long getLCM(long a, long b) {

        return (a / getGCD(a, b)) * b;
    }

    private int getMin(int[] coins) {

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            min = Math.min(min, coin);
        }

        return min;
    }
}