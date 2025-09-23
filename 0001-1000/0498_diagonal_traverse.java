class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        
        // base case
        if(mat==null || mat.length==0)
            return new int[0];
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, List<Integer>> diag = new HashMap<>();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                int curr = i+j;
                diag.computeIfAbsent(curr, a->new ArrayList<>()).add(mat[i][j]);
            }
        }

        int ans[] = new int[m*n];

        int idx = 0;

        for(int i=0; i<m+n-1; i++) {
            List<Integer> list = diag.get(i);
            if(i%2==0) {
                Collections.reverse(list);
            }
            for(int val : list) {
                ans[idx++] = val;
            }
        }

        return ans;

    }
}
