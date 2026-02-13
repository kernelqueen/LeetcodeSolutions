class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxlen = 0;
        
        // Case 1: Only one distinct character
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        for(int i = 0; i < n; ) {
            char ch = s.charAt(i);
            int j = i;
            // Count consecutive occurrences of the same character
            while(j < n && s.charAt(j) == ch) {
                j++;
            }
            int len = j - i;
            // A balanced substring with one distinct char must have even length
            if(len % 2 == 0) {
                maxlen = Math.max(maxlen, len);
            }
            i = j;
        }
        
        // Case 2: Two distinct characters
        // Try all possible pairs: (a,b), (a,c), (b,c)
        // Time Complexity: O(n) for each pair, O(3n) = O(n) total
        // Space Complexity: O(n) for the HashMap
        maxlen = Math.max(maxlen, solve2(s, 'c'));  // Consider only 'a' and 'b'
        maxlen = Math.max(maxlen, solve2(s, 'b'));  // Consider only 'a' and 'c'
        maxlen = Math.max(maxlen, solve2(s, 'a'));  // Consider only 'b' and 'c'
        
        // Case 3: All three distinct characters ('a', 'b', 'c')
        // Time Complexity: O(n)
        // Space Complexity: O(n) for the HashMap
        Map<String, Integer> prev = new HashMap<>();
        int c1 = 0, c2 = 0, c3 = 0;
        
        // Store the state "count_a#count_b" with its first occurrence index
        prev.put("0#0", -1);
        
        // Example: "abc"
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // Increment counters based on character
            if(ch == 'a') c1++;
            else if(ch == 'b') c2++;
            else c3++;
            
            // Create a state key representing the difference in counts
            // For a balanced substring: count_a == count_b == count_c
            // This is equivalent to: (count_a - count_c) and (count_b - count_c) both being 0
            String key = (c1 - c3) + "#" + (c2 - c3);
            
            // If we've seen this state before, we have a balanced substring
            if(prev.containsKey(key)) {
                maxlen = Math.max(maxlen, i - prev.get(key));
            } else {
                // Store the first occurrence of this state
                prev.put(key, i);
            }
        }
        
        return maxlen;
    }
    
    /**
     * Solves for the longest balanced substring containing exactly two distinct characters
     * by skipping one character type.
     * 
     * @param s The input string
     * @param skip The character to skip (one of 'a', 'b', or 'c')
     * @return The length of the longest balanced substring
     * 
     * Strategy: Use a sliding window approach with prefix difference tracking
     * For a balanced substring with two chars: count(first) == count(second)
     * This is equivalent to: count(first) - count(second) == 0
     */
    int solve2(String s, char skip) {
        int mlen = 0;
        
        // Determine which two characters to consider
        // If skip == 'a', consider 'b' and 'c'
        // If skip == 'b', consider 'a' and 'c'
        // If skip == 'c', consider 'a' and 'b'
        char first = (skip == 'a') ? 'b' : 'a';
        char second = (skip == 'c') ? 'b' : 'c';
        
        int i = 0, n = s.length();
        
        // Process the string in segments, skipping the 'skip' character
        while(i < n) {
            int c1 = 0, c2 = 0;  // Counters for first and second characters
            
            // Map to store first occurrence of each difference value
            // Key: difference (c1 - c2), Value: index
            Map<Integer, Integer> prev = new HashMap<>();
            prev.put(0, i - 1);  // Base case: difference of 0 at position before start
            
            // Example: "ababcababc" with skip='c'
            // Process continuous segments without the skip character
            while(i < n && s.charAt(i) != skip) {
                char ch = s.charAt(i);
                
                // Update counters
                if(ch == first) c1++;
                else c2++;
                
                int diff = c1 - c2;
                
                // If we've seen this difference before, we have a balanced substring
                if(prev.containsKey(diff)) {
                    mlen = Math.max(mlen, i - prev.get(diff));
                } else {
                    // Store the first occurrence of this difference
                    prev.put(diff, i);
                }
                
                i++;
            }
            
            // Skip the 'skip' character
            i++;
        }
        
        return mlen;
    }
}