class Solution {
    public int numSub(String s) {
        
        long mod = 1_000_000_007;

        long ans = 0; 
        int n = s.length();
        for(int i=0; i<n;) {

            // skipping zeroes

            while(i<n && s.charAt(i)=='0')
                i++;
            
            long ones = 0; // consecutive ones

            while(i<n && s.charAt(i)=='1') {
                ones++;
                i++;
            }

            // total substrings
            long currcount = ((ones)%mod * (ones+1)%mod)%mod;
            currcount = (currcount/2)%mod;
            ans = (ans + currcount)%mod;

        }

        return (int)ans;

    }
}

/*
Given String length = n
[1111111...111]

Total strings of length n = 1
Total strings of length n-1 = 2
Total strings of length n-2 = 3
...
Total strings of length 1 = n

Total substrings = 1+2+3+...+n = n*(n+1)/2;
*/