// Approach 1: Simulation of the triangular sum process
// Time Complexity: O(n^2)
// Space Complexity: O(1)
class Solution {
    public int triangularSum(int[] nums) {
        // We simulate the process row by row
        // In each iteration, nums[j] is updated as the sum of nums[j] and nums[j+1], modulo 10
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                nums[j] = (nums[j] + nums[j + 1]) % 10;
            }
        }
        // At the end of this process, the first element contains the triangular sum
        return nums[0] % 10;
    }
}

/*
Example Walkthrough:
Input:  [1,2,3,4,5]
Step 1: [3,5,7,9]       // (1+2), (2+3), (3+4), (4+5) mod 10
Step 2: [8,2,6]         // (3+5), (5+7), (7+9) mod 10
Step 3: [0,8]           // (8+2), (2+6) mod 10
Step 4: [8]             // (0+8) mod 10
Output: 8
*/


// Approach 2: Using Combinatorics (Binomial Coefficients)
// Time Complexity: O(n) (for coefficient computation)
// Space Complexity: O(n)
// This approach avoids simulation by leveraging the fact that the triangular sum
// is a linear combination of input elements with binomial coefficients.
import java.math.BigInteger;

class Solution2 {
    public int triangularSum(int[] nums) {
        int n = nums.length;

        // Array to hold binomial coefficients for row (n-1)
        BigInteger[] coeffs = new BigInteger[n];
        coeffs[0] = BigInteger.ONE;

        // Compute binomial coefficients using iterative formula:
        // C(n, r) = C(n, r-1) * (n-r)/r
        for (int r = 1; r < n; r++) {
            coeffs[r] = coeffs[r - 1]
                .multiply(BigInteger.valueOf(n - r))
                .divide(BigInteger.valueOf(r));
        }

        // Weighted sum of nums[i] with binomial coefficients
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            sum = sum.add(coeffs[i].multiply(BigInteger.valueOf(nums[i])));
        }

        // Result is sum modulo 10
        return sum.mod(BigInteger.TEN).intValue();
    }
}

/*
Example Walkthrough:
Input:  [1,2,3,4,5], n=5
Binomial Coefficients for row 4: [1,4,6,4,1]
Weighted Sum = 1*1 + 4*2 + 6*3 + 4*4 + 1*5 = 1 + 8 + 18 + 16 + 5 = 48
48 mod 10 = 8
Output: 8
*/
