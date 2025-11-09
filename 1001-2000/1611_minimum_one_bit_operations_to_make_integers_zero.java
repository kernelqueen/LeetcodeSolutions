class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0; // binary number corresponding to n graycode number
        while(n > 0) { // O(32)
            ans = ans^n; // xor of all prefixes
            n = n>>1;
        }
        // binary number -- graycode
        return ans;
    }
}
/*










OP1: Flip the rightmost bit (0th bit).

OP2: Flip the ith bit if:

- The (i−1)th bit is 1, and

- All bits below (i−1) are 0.

At one time we are able to flip at most one bit: --> 
1000 → 1001 → 1011 → 1010 → 1110 → 1111 → 1101 → 1100 → 0100




011
100

3 bits 


Gray Code: Special Binary Numbering System where only one bit changes between consecutive numbers.

| Decimal | Binary | Gray Code |
| ------- | ------ | --------- |
| 0       | 000    | 000       |
| 1       | 001    | 001       |
| 2       | 010    | 011       |
| 3       | 011    | 010       |
| 4       | 100    | 110       |
| 5       | 101    | 111       |
| 6       | 110    | 101       |
| 7       | 111    | 100       |


Why we need gray codes?
- Ex - For 3(011) to 4(100) --> there are 3 bit changes, and there can be a transient state when dealing with switches, circuits etc. Which will be error prone, so we use gray code instead. 







Gray code of a number n:
    gray = n ^ (n >> 1)
The most significant bit (MSB) of Gray code is the same as in binary.

Gray to Binary Number: XOR all prefixes
    binary = gray ^ (gray >> 1) ^ (gray >> 2) ^ ... until 0

INPUT : n -- gray code number --> binary number

Ex : 011 ^ 001 ^ 000 = 010 which is 2

How does it fit to our problem?

| Step | Binary | Operation Description                                            |
| ---- | ------ | ---------------------------------------------------------------- |
| 0    | 000    | Start (0)                                                        |
| 1    | 001    | Flip rightmost bit                                               |
| 2    | 011    | Filp 1                                                           |
| 3    | 010    | Flip rightmost bit                                               |
| 4    | 110    | Flip bit 2                                                       |
| 5    | 111    | Flip bit 0                                                       |
| 6    | 101    | Flip bit 1                                                       |
| 7    | 100    | Flip bit 0                                                       |

Given n = A number in Gray code order

*/