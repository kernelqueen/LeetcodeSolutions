class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left=0, right = 0, dash = 0;
        // O(n)

        for(int i=0; i<moves.length(); i++) {
            if(moves.charAt(i)=='L')
                left++;
            else if(moves.charAt(i)=='R')
                right++;
            else dash++;
        }

        return Math.max(left+dash-right, right+dash-left);
    }
}