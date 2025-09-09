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
