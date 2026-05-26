class Solution {
    public int numberOfSpecialChars(String word) {
        int f1[] = new int[26];
        int f2[] = new int[26];

        for(char ch : word.toCharArray()) { // O(n)
            if(ch>='a' && ch<='z')
                f1[ch-'a']++;
            else f2[ch-'A']++;
        }

        int count = 0;

        for(int i=0; i<26; i++) {
            if(f1[i]>0 && f2[i]>0)
                count++;
        }

        return count;
    }
}