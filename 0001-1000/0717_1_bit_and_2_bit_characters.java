class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length; 
        // base case
        if(n==1) {
            return true; // "0"
        }  

        if(bits[n-1]==0 && bits[n-2]==0) {
            return true;
        }

        for(int i=1; i<bits.length; i++) {
            //[1,0,1,0,1,1,0]
            // [1,1,1,0]
            //  0 1 2 3. i = 4
            if(bits[i-1]==1) {
                i++;
            }
            if(i==n-1) {
                return true;
            }
        }

        return false;
    }
}

/*

Only three chars we can find: "0", "10", "11"

Notice that a 1 is always accompained by a 0 or 1.


Observations:
1. If there is only single char, that is has to be one bit. "0" , as "1" is invalid.
2. If there are two consecutive 0s at the end, then last char is for sure one bit. Ex: [1,0,0], [0,0,0], [1,1,1,0,0]
3. For all the other cases we can traverse array
    - We can pair each 1 with whatever is on its right - "0" or "1"
    - If there is only one char left at end, then we return true, otherwise false.
    Ex: [1,1,0] - true
        [1,1,1,0] - false
        [0,1,1,0] - true
        [0] - true
        [1,0,0] - true

*/