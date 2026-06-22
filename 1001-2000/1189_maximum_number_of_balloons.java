class Solution {
    public int maxNumberOfBalloons(String text) {
        
        int freq[] = new int[26];
        for(int i=0; i<text.length(); i++) {
            freq[text.charAt(i)-'a']++;
        }

        int min = Integer.MAX_VALUE;

        // char b
        min = Math.min(freq['b'-'a'], min);
        min = Math.min(freq['a'-'a'], min);
        min = Math.min(freq['l'-'a']/2, min);
        min = Math.min(freq['o'-'a']/2, min);
        min = Math.min(freq['n'-'a'], min);

        return min;

    }
}