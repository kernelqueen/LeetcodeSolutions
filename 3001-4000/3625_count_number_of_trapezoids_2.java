import java.util.*;

class Solution {

    private String frac(int a, int b) {
        if (b == 0) return "1/0";          
        int g = gcd(Math.abs(a), Math.abs(b));
        a /= g; b /= g;
        if (b < 0) { a = -a; b = -b; }     
        return a + "/" + b;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;

        int traps = 0;     
        int parallelograms = 0;

        // For trapeziums
        Map<String,Integer> slopeCount = new HashMap<>();
        Map<String,Integer> lineCount = new HashMap<>();

        // For parallelograms
        Map<String,Integer> slopeDistCount = new HashMap<>();
        Map<String,Integer> collinearDistCount = new HashMap<>();

        for (int i = 0; i < n; i++) { // O(n^2)
        // O(n)
            int[] p2 = points[i];
            for (int j = i+1; j < n; j++) {
                int[] p1 = points[j];

                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];

                String slope = (dx != 0) ? frac(dy, dx) : "inf";

                String intercept;
                if (!slope.equals("inf")) {
                    intercept = frac(p1[1] * dx - dy * p1[0], dx);
                } else {
                    intercept = "x=" + p1[0];
                }

                String lineKey = slope + "|" + intercept;

                int dist = (dx * dx) + (dy * dy);

                String slopeDistKey = slope + "|" + dist;
                String collinearDistKey = slope + "|" + intercept + "|" + dist;

                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                lineCount.put(lineKey, lineCount.getOrDefault(lineKey, 0) + 1);

                traps += slopeCount.getOrDefault(slope, 0)
                               - lineCount.getOrDefault(lineKey, 0);

                slopeDistCount.put(slopeDistKey, slopeDistCount.getOrDefault(slopeDistKey, 0) + 1);
                collinearDistCount.put(collinearDistKey, collinearDistCount.getOrDefault(collinearDistKey, 0) + 1);

                parallelograms += slopeDistCount.getOrDefault(slopeDistKey, 0)
                                - collinearDistCount.getOrDefault(collinearDistKey, 0); // twice 

                
            }
        }

        // each parallelogram counted twice
        return traps - parallelograms / 2;
    }
}
