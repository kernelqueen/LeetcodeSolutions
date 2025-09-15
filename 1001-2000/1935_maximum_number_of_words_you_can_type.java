class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        

        // text String = n length
        // brokenLetters = m length [26 chars]
        // O(n)
        String words[] = text.split(" ");

        int ans = 0; // to store the words we can type

        // O(K*m*l) = O(n * m)
        for(String word : words) { // O(K)

            boolean canType = true;
            for(char ch : brokenLetters.toCharArray()) { // O(m)
                if(word.indexOf(ch)!=-1) { // O(l)
                    canType = false;
                    break;
                }
            }

            if(canType) {
                ans++;
            }

        }

        return ans;

    }
}

// T.C. = O(n*m)
// S.C = O(n)