class Solution {
    public int minOperations(String s) {
        int zerostart = 0, onestart = 0;

        for(int i=0; i<s.length(); i++) {
            // O(n)
            // O(1)
            // 0 0 0 0 0
            // 0 1 2 3 4
            // zerostart = 1,2 [01010]
            // onstart = 1,2,3 [10101]
            char ch = s.charAt(i);
            if(i%2==0) {
                if(ch=='0')
                    onestart++;
                else zerostart++;
            }
            else {
                if(ch=='0') 
                    zerostart++;
                else onestart++;
            }

        }
        return Math.min(zerostart, onestart);
    }
}




/*

Solution Appraoch:

A string can either start with 1 or 0

Starts with 1-
count all the ops, where we need to flip the bit 

1 1 1 1 --> 1 0 1 0 -- 2 ops


Starts with 0 -
count all the ops, where we need to flip the bit

0 0 0 0 -- > 0 1 0 1 -- 2 ops
0 0 0 0 --> 1 0 1 0 --  2 ops

min(2,2) --  2

*/