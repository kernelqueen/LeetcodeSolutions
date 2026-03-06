class Solution {
    public boolean checkOnesSegment(String s) {
        int i=0, n = s.length();
        
        while(i<n && s.charAt(i)=='1')
            i++;
        while(i<n && s.charAt(i)=='0')
            i++;
        return i>=n ? true : false;
    }
}