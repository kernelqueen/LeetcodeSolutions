/**  HashSet Approach **/
class Solution {
    public int countPalindromicSubsequence(String s) {
        
        int first[] = new int[26];
        int last[] = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        int ans = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(first[ch-'a']==-1) {
                first[ch-'a'] = i; // first occurrence of the char
            }
            last[ch-'a'] = i; // update in any case
        }

        for(int i=0; i<26; i++) {
            // checking for palindromes for all chars between those first and last index

            if(first[i]==-1)
                continue;
            int startindex = first[i];
            int endindex = last[i];
            HashSet<Character> hset = new HashSet<>();
            for(int j=startindex+1; j<endindex; j++) {
                hset.add(s.charAt(j));
            }
            ans += hset.size();
        }

        return ans;

    }
}

/*** Bitmask Approach ****/
class Solution {
    public int countPalindromicSubsequence(String s) {
        
        int first[] = new int[26];
        int last[] = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        int ans = 0;

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(first[ch-'a']==-1) {
                first[ch-'a'] = i; // first occurrence of the char
            }
            last[ch-'a'] = i; // update in any case
        }

        for(int i=0; i<26; i++) {
            // checking for palindromes for all chars between those first and last index

            if(first[i]==-1)
                continue;
            int startindex = first[i];
            int endindex = last[i];
            HashSet<Character> hset = new HashSet<>();
            int bitmask = (1<<26) - 1; // 1111....111
            for(int j=startindex+1; j<endindex; j++) {
                //hset.add(s.charAt(j));
                int currbit = 1 << (s.charAt(j)-'a');
                if((currbit & bitmask) > 0)
                    ans++;  // this char is not touched yet
                bitmask = bitmask & (~currbit); // NOT of the bit is AND, to make sure its position now becomes zero for further calculations

            }
            //ans += hset.size();
        }

        return ans;

    }
}