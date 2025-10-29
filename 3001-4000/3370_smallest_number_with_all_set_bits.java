/* ----Approach 1 using while loop ---- */
class Solution {
    public int smallestNumber(int n) {
        
        int count = 0;

        while( n > 0) {
            n = n>>1;
            count++;
        }

        int num = 1;

        num = num<<count; // 1000

        return num-1;

    }
}
/*
Ex - 5 = 101 --> 111 = 7
    10 = 1010 --> 1111 = 15 
    7 = 111 --> 111 -- > 7 

    5 -- > 3 --> 111
    1 = 10 = 100 = 1000 - 1 = 111 = 7
*/

/*----Approach 2 using Java inbuilt function ---- */
class Solution {
    public int smallestNumber(int n) {
        // For 5, it will return 4 as answer, as that has the msb bit set
        int msb = Integer.highestOneBit(n);
        return (msb << 1) - 1;

    }
}