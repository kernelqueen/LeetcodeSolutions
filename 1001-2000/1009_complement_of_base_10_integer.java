class Solution {
    public int bitwiseComplement(int n) {
        // base case 
        if(n==0)
            return 1;
        // O(32) = O(1)
        int hbit = Integer.highestOneBit(n)
<<1;
        return n ^ (hbit - 1);
    }
}

/*

1 0 1 XOR with
1 1 1

ans = 0 1 0

Take Highest Bit
1 0 0 
left Shift
<<1
1 0 0 0
Minus one 
0 1 1 1


*/