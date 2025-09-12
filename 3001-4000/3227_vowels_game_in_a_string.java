class Solution {
    public boolean doesAliceWin(String s) {
        
        for(int i=0; i<s.length(); i++) {
            if("aeiou".indexOf(s.charAt(i))!=-1) {
                return true;
            }
        }

        // no vowel in the string
        return false;

    }
}

/*
even no of non-zero vowels - 2,4,6,8
odd no of vowels - [str], 

hgdsk --> only case where Alice will loose



*/