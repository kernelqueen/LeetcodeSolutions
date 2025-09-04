class Solution {
    public long flowerGame(int n, int m) {
        // n + m = odd
        // [1,n] = x
        // [1,m] = y

        // first case
        long neven = n/2;
        long modd = (m%2==0) ? m/2 : (m/2)+1;

        // second  case
        long meven = m/2;
        long nodd = (n%2==0) ? n/2 : (n/2)+1;

        long ans = neven*modd + meven*nodd;
        return ans;
    }
}

/*
Solution Approach:

x chosen from [1,n]
y chosen from [1,m]

x+y = odd
xeven -- yodd
xeven = 

yodd -- xeven
n = 3, m = 2
neven = [2]
modd = [1]

nodd = [1,3] = 2
meven = [2] = 1
ans = 1*1 + 2*1


Ex - 2
n = 6, m = 7
neven = [2, 4, 6]
modd = [1, 3, 5, 7]

ans = 3*4 = 12 pairs

nodd = [1, 3, 5]
meven = [2, 4, 6]

ans = 3*3 = 9 pairs

total = 12+9 = 21

*/
