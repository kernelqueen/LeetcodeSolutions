class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char s[] = new char[n];

        char ch = 'a';
        for(int i=0; i<n; i++) {
            // already assigned by an earlier position that shared a prefix with i
            if(s[i] > 0)
                continue;
            if(ch > 'z')
                return "";
            s[i] = ch;
            // lcp[i][j] > 0 means s[i]==s[j], propagate the same char
            for(int j=i+1; j<n; j++) {
                if(lcp[i][j] > 0) {
                    s[j] = s[i];
                }
            }
            ch++;
        }

        // verify s produces the given lcp using: lcp[i][j] = 1 + lcp[i+1][j+1] if s[i]==s[j], else 0
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int prefix;
                if(s[i]==s[j]) {
                    int prevpref = 0;
                    if(i==n-1 || j==n-1)
                        prevpref = 0;
                    else prevpref = lcp[i+1][j+1];
                    prefix = 1+prevpref;
                }
                else prefix = 0;
                if(prefix!=lcp[i][j])
                    return "";
            }
        }

        return String.valueOf(s);
    }
}