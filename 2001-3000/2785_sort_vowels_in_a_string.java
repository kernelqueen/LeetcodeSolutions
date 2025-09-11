class Solution {
    public String sortVowels(String s) {
        
        List<Character> list = new ArrayList<>();

        for(int i=0; i<s.length(); i++) {

            if(isVowel(s.charAt(i)))
                list.add(s.charAt(i));

        }

        Collections.sort(list); // on the basis of ASCII

        StringBuilder sb = new StringBuilder();

        int index = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(isVowel(ch)) {
                sb.append(list.get(index++));
            }
            else sb.append(ch);
        }

        return sb.toString();
    }

    private boolean isVowel(char ch) {
        return "aieouAEIOU".indexOf(ch)!=-1;
    }
}

/*
Example Explaination:

lEetcOde

A-65 a-97 // Ascii values of the chars
EeOe // list of all the vowels

EOee // sorted list

lEOtcede // answer



*/