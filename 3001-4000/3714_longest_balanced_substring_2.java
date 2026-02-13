class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxlen = 0;

        // Case 1: Only one distinct character
        // For a balanced substring with one char, any length works (all chars are equal)
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        for(int i=0; i<s.length(); ) {
            char ch = s.charAt(i);
            int len = 0;
            // Count consecutive occurrences of the same character
            while(i<n && ch==s.charAt(i)) {
                i++;
                len++;
            }
            maxlen = Math.max(maxlen, len);
        }

        // Case 2: Two distinct characters in the string
        // Try skipping each character to consider pairs: (b,c), (a,c), (a,b)
        // Time Complexity: O(n) for each call, O(3n) = O(n) total
        maxlen = Math.max(maxlen, getLength(s, 'a')); // Consider 'b' and 'c'
        maxlen = Math.max(maxlen, getLength(s, 'b')); // Consider 'a' and 'c'
        maxlen = Math.max(maxlen, getLength(s, 'c')); // Consider 'a' and 'b'

        // Case 3: All three distinct characters are present
        // For balanced substring: count_a == count_b == count_c
        // Equivalent to: (a-b) == 0 AND (a-c) == 0
        // We track differences and find when the same state repeats
        // Time Complexity: O(n)
        // Space Complexity: O(n) for HashMap
        HashMap<String, Integer> prev = new HashMap<>();
        int c1=0, c2=0, c3=0;
        // Initialize with base state: all counts are 0 at index -1
        prev.put("0#0", -1);
        // Example: "abc"
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            // Increment counters for each character
            if(ch=='a')
                c1++;
            else if(ch=='b')
                c2++;
            else c3++;
            
            // Calculate differences to track balance
            int diff1 = c1-c2; // Difference between 'a' and 'b' counts
            int diff2 = c1-c3; // Difference between 'a' and 'c' counts
            String key = diff1 + "#" + diff2;
            
            // If we've seen this state before, substring from that point to now is balanced
            if(prev.containsKey(key)) {
                maxlen = Math.max(maxlen, i-prev.get(key));
            } else {
                // Store first occurrence of this state
                prev.put(key, i);
            }
        }

        return maxlen;

    }

    int getLength(String s, char skip) {
        int mlen = 0;
        // a --> consider b, c
        // b --> consider a, c
        // c --> consider a, b
        char first = (skip=='a') ? 'b' : 'a';
        char second = (skip=='c') ? 'b' : 'c';
        int i=0, n = s.length();

        // Process string in segments, separated by the skip character
        while( i < n) {
            int c1 = 0, c2 = 0; // Counters for first and second characters
            
            // HashMap stores difference -> first occurrence index
            // For balanced substring: c1 == c2, so c1-c2 == 0
            HashMap<Integer, Integer> prev = new HashMap<>();
            prev.put(0, i-1); // Base case: difference 0 before current segment
            
            // Process continuous segment without skip character
            // Example: "ababcababc" with skip='c'
            while( i < n && s.charAt(i)!=skip) {
                char ch = s.charAt(i);
                // Update counters
                if(ch==first)
                    c1++;
                else c2++;
                
                // Check if we've seen this difference before
                if(prev.containsKey(c1-c2)) {
                    // Substring from prev index to current is balanced
                    mlen = Math.max(mlen, i-prev.get(c1-c2));
                } 
                else prev.put(c1-c2, i); // Store first occurrence of this difference
                i++;
            }
            i++; // Skip the 'skip' character
            
        }

        return mlen;
    }
}