class Solution {
    int mod = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        // Transition matrix T where T[i][j] = 1 if value i can follow value j in a zigzag
        // i.e. i > j (going up) — the down direction is handled by symmetry (*2 at the end)
        // Time: O(m^3 * log n), Space: O(m^2)
        long[][] T = new long[m][m];
        for (int j = 0; j < m; j++) {
            for (int i = m - j; i < m; i++) {
                T[i][j] = 1; // value i is strictly greater than value j
            }
        }

        // T^(n-1) encodes all valid zigzag transitions across n elements
        long[][] powT = matPow(T, n - 1, m);

        // Sum all entries: each (i,j) entry = number of zigzag arrays starting at j ending at i
        long total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                total = (total + powT[i][j]) % mod;
            }
        }

        // Multiply by 2 to account for both up-first and down-first zigzag sequences
        return (int) (total * 2 % mod);
    }

    // Binary exponentiation for matrices
    long[][] matPow(long[][] base, long e, int m) {
        long[][] res = new long[m][m];
        for (int i = 0; i < m; i++) res[i][i] = 1; // Identity matrix

        while (e > 0) {
            if (e % 2 == 1) res = mul(res, base);
            base = mul(base, base);
            e >>= 1;
        }
        return res;
    }

    // Standard matrix multiplication under mod
    long[][] mul(long[][] A, long[][] B) {
        int m = A.length;
        long[][] C = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                long sum = 0;
                for (int k = 0; k < m; k++) {
                    sum = (sum + A[i][k] * B[k][j]) % mod;
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
}