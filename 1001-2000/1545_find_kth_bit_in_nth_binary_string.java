class Solution {
    public char findKthBit(int n, int k) {
        // O(n)
        // O(n) - recursive stack
        if(n==1)
            return '0';
        int len = (int)Math.pow(2,n) - 1;
        
        if(k==(len/2)+1)
            return '1';
        else if(k<=len/2) 
            return findKthBit(n-1,k);
        else {
            char bit = findKthBit(n-1, len-k+1);
            return bit=='0' ? '1' : '0';
        }
    }
}