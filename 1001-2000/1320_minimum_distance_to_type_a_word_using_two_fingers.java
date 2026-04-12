/**************Recursive with Memoization********************** */
class Solution {
    Integer dp[][][];
    public int minimumDistance(String word) {
        dp = new Integer[word.length()][27][27];
        return helper(word, 0, -1, -1);

    }

    int helper(String word, int i, int pos1, int pos2) {
        // pos1, pos2 are char indices (-1 = not placed), passed by value
        if (i == word.length()) return 0;

        int ch = word.charAt(i) - 'A';
        
        if(dp[i][pos1+1][pos2+1]!=null)
            return dp[i][pos1+1][pos2+1];

        // move finger 1
        int d1 = getDist(ch, pos1) + helper(word, i+1, ch, pos2);
        // move finger 2
        int d2 = getDist(ch, pos2) + helper(word, i+1, pos1, ch);

        return dp[i][pos1+1][pos2+1]= Math.min(d1, d2);
    }

    int getDist(int ch, int pos) {
        if (pos == -1) return 0;
        int x1 = ch/6, x2=pos/6, y1=ch%6, y2=pos%6;
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

}

/*******************Iteravtive DP Solution********************* */
class Solution {
    int minimumDistance(String word) {
    int n = word.length();
    // dp[i][pos1][pos2] = min cost to type word[i..] with fingers at pos1, pos2
    int[][][] dp = new int[n + 1][27][27];
    
    
    for (int i = n - 1; i >= 0; i--) {
        int ch = word.charAt(i) - 'A';
        for (int pos1 = 0; pos1 < 27; pos1++) {
            for (int pos2 = 0; pos2 < 27; pos2++) {
                // move finger 1 (pos1-1 to undo the +1 offset)
                int d1 = getDist(ch, pos1-1) + dp[i + 1][ch + 1][pos2];
                // move finger 2
                int d2 = getDist(ch, pos2-1) + dp[i + 1][pos1][ch + 1];
                dp[i][pos1][pos2] = Math.min(d1, d2);
            }
        }
    }
    
    // start: both fingers unplaced → pos1=0, pos2=0 (both are -1+1)
    return dp[0][0][0];
}

int getDist(int ch, int pos) {
        if (pos == -1) return 0;
        int x1 = ch/6, x2=pos/6, y1=ch%6, y2=pos%6;
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}