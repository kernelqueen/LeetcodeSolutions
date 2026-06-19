class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0; // max altitude
        int curr = 0;

        for(int g : gain) { // O(n), O(1)
            curr += g;
            max = Math.max(curr, max);
        }

        return max;
    }
}