class Solution {
    public int minimumSum(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;

        int minarea = Integer.MAX_VALUE;

       // Case 1: Try splitting along rows
        for (int cutRow = 1; cutRow <= rows; cutRow++) {
            // First rectangle covers [0..cutRow)
            int topRect = getBoundingBoxArea(grid, 0, 0, cutRow, cols);
            // First rectangle covers [cutRow..rows)
            int bottomRect = getBoundingBoxArea(grid, cutRow, 0, rows, cols);

            minarea = Math.min(minarea,
                Math.min(
                    /*
                    <<Top Rect + Split Horizontal>>

                        +-----------+
                        |     1     |
                        +-----+-----+
                        |  2  |  3  |
                        +-----+-----+
                    */
                    topRect + splitHorizontal(grid, cutRow, 0, rows, cols),
                    Math.min(
                            /*<<Bottom Rect + Split Horizontal>>
                            +-----+-----+
                            |  1  |  2  |
                            +-----+-----+
                            |     3     |
                            +-----------+*/

                        bottomRect + splitHorizontal(grid, 0, 0, cutRow, cols),

                        /*

                     <<Bottom Rect + Split Vertical>>
                            +-----------+
                            |     1     |
                            +-----------+
                            |     2     |
                            +-----------+
                            |     3     |
                            +-----------+*/
                        bottomRect + splitVertical(grid, 0, 0, cutRow, cols)
                    )
                )
            );
        }

         // Case 2: Try splitting along columns
        for (int cutCol = 1; cutCol <= cols; cutCol++) {
            // First rectangle covers [0..cutCol)
            int leftRect = getBoundingBoxArea(grid, 0, 0, rows, cutCol);
            // First rectangle covers [cutCol..cols)
            int rightRect = getBoundingBoxArea(grid, 0, cutCol, rows, cols);

            minarea = Math.min(minarea,
                Math.min(
                    leftRect + splitVertical(grid, 0, cutCol, rows, cols),
                    Math.min(
                        rightRect + splitVertical(grid, 0, 0, rows, cutCol),
                        rightRect + splitHorizontal(grid, 0, 0, rows, cutCol)
                    )
                )
            );
        }

        return minarea;

    }


    // Compute min area using 2 rectangles (split horizontally → left + right)
    /*
        +-----------+
        |     1     |
        +-----+-----+
        |  2  |  3  |
        +-----+-----+
    */
    private int splitHorizontal(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        int minArea = Integer.MAX_VALUE;
        for (int midCol = colStart + 1; midCol <= colEnd; midCol++) {
            int leftArea = getBoundingBoxArea(grid, rowStart, colStart, rowEnd, midCol);
            int rightArea = getBoundingBoxArea(grid, rowStart, midCol, rowEnd, colEnd);
            minArea = Math.min(minArea, leftArea + rightArea);
        }
        return minArea;
    }

    
        private int splitVertical(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        int minArea = Integer.MAX_VALUE;
        for (int midRow = rowStart + 1; midRow <= rowEnd; midRow++) {
            int topArea = getBoundingBoxArea(grid, rowStart, colStart, midRow, colEnd);
            int bottomArea = getBoundingBoxArea(grid, midRow, colStart, rowEnd, colEnd);
            minArea = Math.min(minArea, topArea + bottomArea);
        }
        return minArea;
    }

    private int getBoundingBoxArea(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE;
        int maxRow = -1, maxCol = -1;

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    minCol = Math.min(minCol, j);
                    maxRow = Math.max(maxRow, i);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // No 1s found → area = 0
        if (maxRow == -1) return 0;

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}

/*

Horizontal Split

<<Top Rect + Split Horizontal>>

+-----------+
|     1     |
+-----+-----+
|  2  |  3  |
+-----+-----+

<<Bottom Rect + Split Horizontal>>
+-----+-----+
|  1  |  2  |
+-----+-----+
|     3     |
+-----------+

<<Bottom Rect + Split Vertical>>
+-----------+
|     1     |
+-----------+
|     2     |
+-----------+
|     3     |
+-----------+


Vertical Split

<<left rect + Split Vertical>>

+-----+-----+
|     |  2  |
+  1  +-----|
|     |   3 |
+-----+-----+

<<Right Rect + Split Vertical>>

+-----+-----+
|  1  |     |
+-----+  3  |
|  2  |     |
+-----+-----+


<<Right Rect  + Split Horizontal>>
+-----+-----+-----+
|  1  |  2  |  3  |
+-----+-----+-----+



Yesterday's area finding code:

 private int getBoundingBoxArea(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE;
        int maxRow = -1, maxCol = -1;

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    minCol = Math.min(minCol, j);
                    maxRow = Math.max(maxRow, i);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // No 1s found → area = 0
        if (maxRow == -1) return 0;

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

*/
