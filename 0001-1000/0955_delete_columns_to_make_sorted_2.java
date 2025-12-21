class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length();
        int n = strs.length;
        boolean ordering[] = new boolean[n-1];
        int delcount = 0;

        for(int i=0; i<m; i++) {
            // O(m*n)
            boolean delflag = false;
            for(int j=0; j<n-1; j++) {
                if(ordering[j]==false) {
                    if(strs[j].charAt(i) > strs[j+1].charAt(i)) {
                        delflag = true;
                        break;
                    }
                }
            }
            if(delflag==true) {
                delcount++;
                continue;
            }
            // ordering update
            for(int k=0; k<n-1; k++) {
                if(ordering[k]==false) {
                    if(strs[k].charAt(i) < strs[k+1].charAt(i)) {
                        ordering[k] = true;
                    }
                }
            }
        }
        return delcount;

    }
}
/*
x c
y b
z a
ans = 0

*/