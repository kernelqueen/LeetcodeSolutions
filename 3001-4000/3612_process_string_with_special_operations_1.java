class Solution {
    public String processStr(String s) {

        // Time:  O(2^n) — '#' can double the buffer up to n times: k + 2k + 4k + ... = k*2^m
        // Space: O(2^n) — StringBuilder can grow exponentially with '#' operations

        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);              // Append letter
            } else if (ch == '*') {
                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1);   // Delete last character
            } else if (ch == '#') {
                sb.append(sb);              // Double the current string
            } else if (ch == '%') {
                sb.reverse();               // Reverse the current string
            }
        }

        return sb.toString();
    }
}