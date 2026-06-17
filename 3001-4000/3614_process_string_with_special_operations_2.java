class Solution {
    public char processStr(String s, long k) {

        // Forward pass: simulate all operations to find final string length
        // Time: O(n), Space: O(1)
        long len = 0;
        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') len++;
            else if (ch == '#')         len = len * 2;
            else if (ch == '*')         { if (len > 0) len--; }
            // '%' (reverse) does not affect length
        }

        // k is out of bounds — position doesn't exist in final string
        if (k >= len) return '.';

        // Backward pass: trace where index k came from before each operation
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '*') {
                // '*' deleted the last char, so undo by incrementing length
                len++;
            } else if (ch == '#') {
                // '#' doubled the string — undo by halving
                len = len / 2;
                // If k was in the second half, map it back to the first half
                if (k >= len) k = k - len;
            } else if (ch == '%') {
                // '%' reversed the string — undo by mirroring index
                k = len - 1 - k;
            } else {
                // Letter: it occupied the last slot before being appended
                len--;
                if (len == k) return ch;
            }
        }

        return '.';
    }
}