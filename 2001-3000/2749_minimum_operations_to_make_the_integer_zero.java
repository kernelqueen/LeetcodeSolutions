class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        
        // 2^k-1 ~= 10^9 * k
        // k = 35

        for(long i=1; i<=35; i++) {
            // operations are from i=1 to i=k
            long sum = num1 - num2*i;

            int bitcount = Long.bitCount(sum);

            // main condition
            // 1, 2, 4, 
            // 001, 010, 100, 
            // sum of powers (min) = 2^0 + 2^0 + ... = i (number of operations)
            if(bitcount<=i && i<=sum)
                return (int)i;

        }

        return -1;

    }
}


/*

Solution Approach:

k operations will make num1 0

num1 = 2^i1 + num2 + (2^i2 + num2) + ... + (2^ik + num2)

num1 = (num2) * k + (sum of powers of 2)

sum = num1 - num2*k

// sum of the k powers of two we subtract across the k operations.

// Number of 1 bits in the binary form of sum - min number of powers of 2 needed to build this sum

// if we are doing i operations, we need at least i total from the power-of-two part. so sum must be at least i

32-bit signed int∈[−2.1×10^9,2.1×10^9]
*/
