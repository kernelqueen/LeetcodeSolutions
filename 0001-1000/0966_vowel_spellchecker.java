class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        
        HashSet<String> original =  new HashSet<>();

        HashMap<String, String> caseMap = new HashMap<>();

        HashMap<String, String> vowelMap = new HashMap<>();

        for(String word : wordlist) {
            original.add(word);

            String lowerWord = word.toLowerCase();

            caseMap.putIfAbsent(lowerWord, word);

            String vowelKey = maskVowels(lowerWord);

            vowelMap.putIfAbsent(vowelKey, word);

        }

        String ans[] = new String[queries.length];
        int index = 0;

        for(String q : queries) {
            if(original.contains(q)) {
                ans[index++] = q;
                continue;
            }

            String lowerword = q.toLowerCase();
            if(caseMap.containsKey(lowerword)) {
                ans[index++] = caseMap.get(lowerword);
                continue;
            }

            // 3rd case, vowel insensitive word check

            String vowelKey = maskVowels(lowerword);
            if(vowelMap.containsKey(vowelKey)) {
                ans[index++] = vowelMap.get(vowelKey);
                continue;
            }

            ans[index++] = "";
        }

        return ans;

    }

    // T.C = O(n) + O(m)
    // S.C = O(n)

    String maskVowels(String word) {
        StringBuilder sb = new StringBuilder();
        for(char ch :word.toCharArray()) {
            if("aeiou".indexOf(ch)!=-1) {
                sb.append('#');
            }
            else sb.append(ch);
        }

        return sb.toString();
    }
}

/*


Example Explaination:

wordlist = ["KiTe","kite","hare","Hare"]
queries  = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]

Output   = ["kite","KiTe","KiTe","Hare","hare", "",    "",   "KiTe",  "",   "KiTe"]

Case 1: Exact Match [kite --> kite]
Case 2: Matched if case is not considered [Kite --> KiTe]
Case 3: Matched if vowels are not considered [keti --> KiTe]




original = { "KiTe", "kite", "hare", "Hare" }

caseMap = {
  "kite" -> "KiTe",   // "KiTe" came first
  "hare" -> "hare"    // "hare" came first before "Hare"
}

vowelMap = {
  "k#t#" -> "KiTe",   // from "kite" / "KiTe"
  "h#r#" -> "hare"    // from "hare" / "Hare"
}

*/
