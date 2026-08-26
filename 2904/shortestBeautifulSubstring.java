class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int left = 0;
        int ones = 0;

        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink window if we have more than k ones
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // We have exactly k ones
            if (ones == k) {

                // Remove unnecessary leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                // Shorter is better
                // Same length -> lexicographically smaller is better
                if (len < minLen ||
                        (len == minLen && candidate.compareTo(ans) < 0)) {

                    minLen = len;
                    ans = candidate;
                }
            }
        }

        return ans;
    }
}