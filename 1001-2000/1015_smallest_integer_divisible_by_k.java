class Solution {
    public int smallestRepunitDivByK(int k) {
        

        // base condition
        if(k%2==0 || k%5==0)
            return -1;
        
        int rem = 1%k, count = 1;

        while(rem!=0) {
            rem = (rem*10+1)%k;
            count++;
        } // O(k) ... 0 --> repeated rem --> there is already a smaller number 1111..11

        return count;
    }
}
/*

| K  | count      |     N       |
| -- | ---------- | ----------- |
| 1  | 1          | 1           |
| 3  | 3          | 111         |
| 7  | 6          | 111111      |
| 9  | 9          | 111111111   |
| 11 | 2          | 11          |



*/