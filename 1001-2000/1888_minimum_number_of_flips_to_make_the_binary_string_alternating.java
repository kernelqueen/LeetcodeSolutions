class Solution {
    public int minFlips(String s) {

        String str = s + s; // O(n)
        int n = s.length();
        int ans = Integer.MAX_VALUE;

        int onestart = 0, zerostart = 0;

        for(int i=0; i<2*n; i++) {
            // O(2*n)
            char ch = str.charAt(i);
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
            if(i>=n-1) {
                ans = Math.min(ans, Math.min(zerostart, onestart));

                int prev = i-n+1;
                char pch = str.charAt(prev);
                if(prev%2==0) {
                    if(pch=='0')
                        onestart--;
                    else zerostart--;
                    }
                    else {
                        if(pch=='0') 
                            zerostart--;
                        else onestart--;
                    }
            }

        }

        return ans;

        
    }
}

/*
type 1 - 
1 1 1 0 
1 1 0 1
1 0 1 1
0 1 1 1
s + s
1 1 1 0 1 1 1 0 


*/