/*--------------- Brute Force Solution ---------------*/
class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for(int i=0; i<n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for(int j=0; j<n; j++) {
                if(i==j)
                    continue;
                int x2 = points[j][0], y2 = points[j][1];
                // point i does not lie in left
                if(x1 > x2) 
                    continue;
                // point i does not lie in top
                if(y1 < y2)
                    continue;
                boolean blocked = false;
                for(int k=0; k<n; k++) {
                    if(k==i || k==j)
                        continue; 
                    int xk = points[k][0], yk = points[k][1];
                    if(x1<=xk && xk<=x2 && y2<=yk && yk<=y1)
                    {
                        blocked = true;
                        break;
                    }
                }
                if(!blocked) 
                    ans++;
            }
        }
        return ans;
  }
}

/*--------------- Optimal Solution ---------------*/

class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int ans = 0;

        // Sort points by x-coordinate (ascending).
        // If x is the same, sort by y-coordinate (descending).
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; 
            }
            return a[0] - b[0];
        });

        // Iterate over each point as the "left" point in the pair
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            
            // Keeps track of the maximum y encountered so far (to avoid duplicates)
            int maxY = Integer.MIN_VALUE;

            // Compare with points to the right (greater x)
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1];

                // Valid pair only if y2 <= y1 (point j is below or equal in height)
                if (y2 > y1) continue;

                // Ensure uniqueness: only count if y2 is greater than all previously seen y2
                if (y2 > maxY) {
                    ans++;
                    maxY = y2;
                }
            }
        }

        return ans;
    }
}




/*
Solution Explaination Notes:
1. x1 < x2  and y2 <= y1 (second point is not higher in y)
2. or if x1 == x2, then y1 > y2


(1, 4), (2, 3), (3, 2), (4, 1), (2, 5)

y
↑
6 |                        
5 |        ● (2,5)
4 |    ● (1,4)
3 |        ● (2,3)
2 |            ● (3,2)
1 |                ● (4,1)
0 |__________________________________________→ x
    0    1    2    3    4    5


*/
