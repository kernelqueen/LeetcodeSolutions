class Solution {
    public int minPartitions(String n) {
        int max = 0;
        // O(n)
        for(int i=0; i<n.length(); i++) {
            int ch = n.charAt(i)-'0';
            max = Math.max(max, ch);
        }
        return max;
    }
}

/*
Solution Approach

n = 124343
n = 013232
n = 002121
n = 001010
n = 000000
- 111111
- 011111
- 001111
- 001010
*/