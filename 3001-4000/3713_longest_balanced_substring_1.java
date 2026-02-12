class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxlen = 0;
        // O(n^2)
        for(int i=0; i<n; i++) {
            int freq[] = new int[26]; // O(1)
            for(int j=i; j<n; j++) {
                freq[s.charAt(j)-'a']++;
                if((j-i+1) > maxlen && checkBalance(freq)) {
                    maxlen = Math.max(maxlen,j-i+1);
                }
            }

        }

        return maxlen;
    }

    boolean checkBalance(int freq[]) {
        int expect = 0;
        for(int f : freq) {
            if(f==0)
                continue;
            if(expect==0)
                expect = f;
            else if(f!=expect) {
                return false;
            }
        }
        return true;
    }
}



