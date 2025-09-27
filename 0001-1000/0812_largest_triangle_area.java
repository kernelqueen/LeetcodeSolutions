class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Calculate side lengths of the triangle
                    double a = getDist(points[i], points[j]);
                    double b = getDist(points[j], points[k]);
                    double c = getDist(points[k], points[i]);

                    // Semi-perimeter
                    double S = (a + b + c) / 2.0;

                    // Heron's formula: Area^2 = S(S-a)(S-b)(S-c)
                    double radicand = S * (S - a) * (S - b) * (S - c);

                    // Numerical safeguard: avoid negative due to floating-point precision
                    radicand = Math.max(0.0, radicand);

                    double area = Math.sqrt(radicand);

                    // Track the maximum area
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    // Helper method to compute Euclidean distance between two points
    private double getDist(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}

/*
Approach 1: Heron's Formula (Triangle Area from Side Lengths)

1. Compute all three side lengths: a, b, c
2. Compute semi-perimeter: S = (a + b + c) / 2
3. Apply Heron's formula:
      Area = sqrt( S * (S - a) * (S - b) * (S - c) )
4. Take max across all possible triangles
*/

/*------------Approach 2------------ */
class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int x1 = points[i][0], y1 = points[i][1];
                    int x2 = points[j][0], y2 = points[j][1];
                    int x3 = points[k][0], y3 = points[k][1];

                    // Shoelace formula for triangle area:
                    // Area = 1/2 * | x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2) |
                    double area = 0.5 * Math.abs(
                        x1 * (y2 - y3) +
                        x2 * (y3 - y1) +
                        x3 * (y1 - y2)
                    );

                    // Track the maximum area
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

}

/*
Approach 2: Shoelace Formula (Triangle Area from Coordinates)

For vertices (x1, y1), (x2, y2), (x3, y3):

    Area = 1/2 * | x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2) |

- Works directly with coordinates
- Faster (no square root needed except abs)
- Generalizable to any polygon
*/
