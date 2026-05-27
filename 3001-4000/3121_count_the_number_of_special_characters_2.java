class Solution {
    public int numberOfSpecialChars(String word) {

        int[] lower = new int[26];
        int[] upper = new int[26];
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1);

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                // Track the last occurrence of each lowercase letter
                lower[ch - 'a'] = i;
            } else {
                // Track the first occurrence of each uppercase letter
                if (upper[ch - 'A'] == -1)
                    upper[ch - 'A'] = i;
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            // Special char: both cases exist and last lowercase appears before first uppercase
            if (lower[i] != -1 && upper[i] != -1 && lower[i] < upper[i])
                count++;
        }

        return count;
    }
}