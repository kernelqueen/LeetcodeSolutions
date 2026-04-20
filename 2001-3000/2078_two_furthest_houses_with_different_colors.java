class Solution {
    public int maxDistance(int[] colors) {
        int first = 0, second = -1;
        int dist = 0;

        for(int i=0; i<colors.length; i++) {
            // O(n), O(1)
            if(colors[i]!=colors[first]) {
                dist = Math.max(dist, i-first);
                if(second==-1)
                    second = i;
            } else {
                if(second!=-1)
                    dist = Math.max(dist, i-second);
            }
        }

        return dist;
    }
}