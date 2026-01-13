class Solution {

    // Finds the horizontal y = k line that splits the total square area into two equal halves
    public double separateSquares(int[][] squares) {

        // left  → lowest possible y-coordinate where the cut can be
        // right → highest possible y-coordinate where the cut can be
        double left = Double.MAX_VALUE, right = 0;

        // total area of all squares combined
        double totalArea = 0.0;

        // Step 1: Determine search space and total area
        // Time: O(n)
        for (int[] square : squares) {

            // y-coordinate of bottom edge of square
            double y = square[1];

            // side length of square
            double side = square[2];

            // add square's full area
            totalArea += side * side;

            // lowest possible cut line
            left = Math.min(left, y);

            // highest possible cut line (top edge of square)
            right = Math.max(right, y + side);
        }

        // Step 2: Binary search to find y such that
        // area above the line == area below the line
        // Each iteration computes area in O(n)
        while (right - left > 1e-5) {

            // candidate y-line
            double mid = (left + right) / 2.0;

            // area above y = mid
            double aboveArea = getArea(mid, squares);

            // area below y = mid
            double belowArea = totalArea - aboveArea;

            // If area above is larger, move the cut upward
            if (aboveArea > belowArea) {
                left = mid;
            }
            // Otherwise, move the cut downward
            else {
                right = mid;
            }
        }

        // left (or right) converges to the required y-coordinate
        return left;
    }

    // Computes total area of square parts lying ABOVE y = yline
    // Time: O(n)
    double getArea(double yline, int[][] squares) {

        double area = 0.0;

        for (int[] square : squares) {

            double y = square[1];     // bottom edge
            double side = square[2];  // side length

            // Case 1: Square is fully above the cut
            if (y >= yline) {
                area += side * side;
            }

            // Case 2: Square is partially above the cut
            else if (y + side > yline) {
                area += (y + side - yline) * side;
            }

            // Case 3: Square is fully below the cut → contributes 0
        }

        return area;
    }
}
