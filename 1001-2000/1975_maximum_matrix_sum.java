class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int count = 0; // negative
        long ans = 0l;
        int m = matrix.length, n = matrix[0].length;
        long sum = 0;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] < 0) {
                    count++;
                }
                sum += Math.abs(matrix[i][j]);
                min = Math.min(min, Math.abs(matrix[i][j]));

            }
        }

        if(count%2==0) {
            return sum;
        }
        else {
            return sum - 2*min;
        }

    }
}