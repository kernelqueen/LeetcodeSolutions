class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;

        for(int i=0; i<points.length-1; i++) {
            // O(n)
            // O(1)
            int x1 = points[i][0];
            int y1 = points[i][1];
            int x2 = points[i+1][0];
            int y2 = points[i+1][1];

            int xdiff = Math.abs(x1-x2);
            int ydiff = Math.abs(y1-y2);

            if(xdiff < ydiff) {
                ans += ydiff;
            } else {
                ans += xdiff;
            }
        }

        return ans;

    }
}