public class Solution {
    public int maxFreqSum(String s) {
        int freq[] = new int[26];

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            freq[ch-'a']++;
        }

        int maxVowel = 0;
        int maxCons = 0;

        for(int i=0; i<26; i++) {
            if(i==0 || i==4 || i==8 || i==14 || i==20) {
                maxVowel = Math.max(maxVowel, freq[i]);
            } else {
                maxCons = Math.max(maxCons, freq[i]);
            }
        }

        return maxVowel + maxCons;

    }
}

/*

Indices mapping for vowels

a → 0

e → 4

i → 8

o → 14

u → 20

*/ {
    
}
