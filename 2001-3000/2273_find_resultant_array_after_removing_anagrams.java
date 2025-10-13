class Solution {
    public List<String> removeAnagrams(String[] words) {
        
        List<String> ans = new ArrayList<>();

        for(int i=0; i<words.length; i++) {
            // O(nklogk)
            int j = i+1;
            while( j <words.length) {
                if(!isAnagram(words[i], words[j])) {
                    break;
                }
                j++;
            }

            ans.add(words[i]);
            i = j-1; // i+1-1 = i
        }

        return ans;

    }

    boolean isAnagram(String a, String b) {
        // klogk
        char arr1[] = a.toCharArray();
        char arr2[] = b.toCharArray();
        // abba, abab
        //[a, b, b, a] [a, b, a, b]
        // [a,a,b,b] [a,a,b,b]
        //  aabb, aabb

        Arrays.sort(arr1); Arrays.sort(arr2);
        if(String.valueOf(arr1).equals(String.valueOf(arr2))) {
            return true;
        }
        return false;
    }

}







/*
Ex: 1 
["abba","baba","bbaa","cd","cd"]
i = 0, j = i+1
i = j

Ex: 2
   [aa, ba, aa, ba, ca, ac, ad] // Output: [aa, ba, aa, ba, ca, ad ]
i = 0    1  2   3   4   5   6

j = i+1

*/