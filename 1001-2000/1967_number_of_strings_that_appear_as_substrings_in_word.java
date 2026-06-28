class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;

        for(String s : patterns) {
            // n*k*m
            // Optimal - (k+m)*n by KMP Algorithm 
            if(word.indexOf(s)!=-1)
                count++;
        }
        return count;
    }
}