class Solution {
    public int maximalRectangle(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[] height = new int[n];
        int ans = 0;

        // first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                height[j] = 1;
            }
        }
        ans = maxRectangleArea(height);

        // remaining rows
        // O(m*(n+n))
        // O(n)
        for (int i = 1; i < m; i++) {
            // accumulate heights 
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            ans = Math.max(ans, maxRectangleArea(height));
        }

        return ans;
    }

    int maxRectangleArea(int[] height) {
        int max = 0;
        Stack<int[]> st = new Stack<>();

        for (int i = 0; i <= height.length; i++) {
            int currHeight = (i == height.length) ? 0 : height[i];
            int start = i;

            while (!st.isEmpty() && st.peek()[0] > currHeight) {
                int[] temp = st.pop();
                int h = temp[0], index = temp[1];
                // 3,3,3,1
                // 1,1,1,1 ,<1,0>,<0,4>
                // 1*4 = 4, maxarea = 9
                max = Math.max(max, h * (i - index));
                start = index;
            }
            st.push(new int[]{currHeight, start});
        }
        return max;
    }
}
