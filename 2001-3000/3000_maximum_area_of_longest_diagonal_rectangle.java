class Solution {
    public int areaOfMaxDiagonal(int[][] sides) {
        int maxarea = 0;
        int maxdiag = 0;

        for(int i=0; i<sides.length; i++) {
            int currdiag = sides[i][0]*sides[i][0] + sides[i][1]*sides[i][1];

            if(currdiag >= maxdiag) {

                maxarea = (currdiag==maxdiag) ? Math.max(maxarea, sides[i][0]*sides[i][1]) : sides[i][0]*sides[i][1];
                maxdiag = currdiag;

            }
        }

        return maxarea;
    }
}

/*
+--------+
|\      /|
| \    / |
|  \  /  |
|   \/   |
|   /\   |
|  /  \  |
| /    \ |
|/      \|
+--------+


C^2 = a^2 + b^2

*/
