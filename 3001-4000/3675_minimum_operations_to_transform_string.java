class Solution {
    public int minOperations(String s) {
        
        // Step 1: Frequency array to count occurrences of each character
        int freq[] = new int[26];

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch-'a']++;
        }

        // 'prev' represents the "previous higher index" of a character (starting from 26)
        int prev = 26;
        int ops = 0;

        // Step 2: Traverse characters from 'z' (index 25) down to 'b' (index 1)
        for(int i=25; i>0; i--) {
            if(freq[i] > 0) {
                // Calculate the "distance" (hops) we need to shift this character
                int hops = (prev - i);
                ops += hops;     // Add to total operations
                prev = i;        // Update 'prev' to this index for the next iteration
            }
        }

        // Return total operations needed
        return ops;
    }
}
/*
Example - leetcode
freq  index hops
c: 1  [2]   1
d: 1  [3]   1
e: 3  [4]   7
l: 1  [11]  3
o: 1  [14]  5
t: 1  [19]  7

z: __ [25]
a: __ [26]

*/
