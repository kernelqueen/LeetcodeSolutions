class Solution {
    public boolean hasAlternatingBits(int n) {
        int hbit = Integer.highestOneBit(n);
        int num = (hbit << 1) - 1;

        if(num == ((n >> 1) ^ n))
            return true;
        return false;
    }
}
/*

n =    1010
n>>1 = 0101

XOR =  1111

int hbit = Integer.highestOneBit(n)

hbit =    1000

hbit<<1 = 10000

minus one = 1111


*/