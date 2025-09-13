class Solution {
    public int countBinaryPalindromes(long n) {
        
        // Special case: n = 0 (only "0" is a palindrome)
        if (n == 0) 
            return 1;

        // ------------------------------------------------------------
        // Step 1: Find the number of bits in n
        // Example: n = 9 (1001 in binary) → bits = 4
        // ------------------------------------------------------------
        int bits = 64 - Long.numberOfLeadingZeros(n);

        // Start with count = 1 to include "0" as a valid palindrome
        int count = 1;

        // ------------------------------------------------------------
        // Step 2: Count all palindromes with bit-length < bits
        // Formula: 2^((len+1)/2 - 1) palindromes for each length
        // Why? First bit must be 1, remaining (halfLen-1) bits are free
        // ------------------------------------------------------------
        for (int len = 1; len < bits; len++) {
            int halfbits = (len + 1) / 2;
            int palindromes = 1 << (halfbits - 1); // 2^(halfbits-1)
            count += palindromes;
        }

        // ------------------------------------------------------------
        // Step 3: Handle palindromes with exactly 'bits' length
        // Extract prefix (first half of bits of n)
        // ------------------------------------------------------------
        int halfLen = (bits + 1) / 2;        
        long prefix = n >> (bits - halfLen); // keep top half of n

        // Smallest possible prefix (must start with '1')
        long minPrefix = (1L << (halfLen - 1));

        // Count palindromes formed by smaller prefixes than current prefix
        count += (int)(prefix - minPrefix);

        // ------------------------------------------------------------
        // Step 4: Build palindrome candidate from current prefix
        // Mirror prefix → full palindrome
        // ------------------------------------------------------------
        String s = Long.toBinaryString(prefix);
        String r;

        if (bits % 2 == 0) {
            // Even length → mirror full prefix
            r = new StringBuilder(s).reverse().toString();
        } else {
            // Odd length → skip middle bit before mirroring
            r = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
        }

        long candidate = Long.parseLong(s + r, 2); // binary → decimal

        // If candidate ≤ n, include it in count
        if (candidate <= n) {
            count++;
        }

        return count;
    }
}
/*

Solution Appraoch:


1 bit = 2
2 bit = 1
3 bit = 2
4 bit = 2 [1001, 1111]

// step 1 - couting all the binary palindromic numbers
Ex:

Even
_ _ _ _   = 4 bits

Odd
_ _ _ _ _ = 5 bits

halfbits = 2, 3

totalbits = len

halfbits = (len+1)/2 


1 _ _ X X
no of bits = 5


1 0 0 0 1
1 0 1 0 1
1 1 0 1 1
1 1 1 1 1

2 * 2 = 4 [permutations] = 2^(no of half bits-1)

no of binary palindromes = 4

len = total no of bits in the number
halfbits = (len+1)/2

2 ^ halfbits-1 = count of binary palindromes for bits of length len

2 ^ ((len+1)/2-1) = len bits binary palindromes

9 = 1001 = 4 bits

1 bit palindromes
2 bit palindromes
3 bit palindromes
and so on...

// Handling the numbers with same bits as n

Example:
4 bit = 1111  [halflen = 2]
5 bit = 11111 [halflen = 3]

Count the numbers with smaller prefixes but same bit length

Getting the prefix of the given number n
prefix = n >> (lenofbits - halfLen);
our prefix = 1 1  and 1 1 1

get the min prefix for this bit length
1L << (halfLen - 1)
4 bits = [1 0 ]
5 bits = [1 0 0 ]   

difference = prefix - min prefix = possible binary palindromes

5 bits = [1 0 0 _ _]   
Ex - 100XX, 101XX, 110XX


halfbits = (len+1)/2;
Example:
4 bit = 1111
5 bit = 11111


remove the mirrored half bits of this number
n >> (bits - halfbits) 

4 bit = 11
5 bit = 111

s4 = "11"
s5 = "111"

// get the palindrome using string

s4 = "1111"
s5 = "11111"  // Ex: For 11100 , prefix = 111,  palindrome = 11111
*/