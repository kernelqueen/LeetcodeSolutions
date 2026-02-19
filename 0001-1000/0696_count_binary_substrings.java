class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int currcount = 1, prevcount = 0;

        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i)==s.charAt(i-1)) {
                currcount++;
            }
            else {
                prevcount = currcount;
                currcount = 1;
            }
            if(currcount <=prevcount)
                ans++;
        }

        return ans;
        
    }
}