/************************Using Count Array************************* */

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int ans[] = new int[n];

        int count[] = new int[n+1];
        int common = 0;

        for(int i=0; i<n; i++) {
            count[A[i]]++;

            if(count[A[i]] == 2)
                common++;
            count[B[i]]++;
            if(count[B[i]] == 2)
                common++;
            ans[i] = common;

        }
        return ans;
    }
}

/************************Using Bit Masking************************* */
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        long bitsA = 0l, bitsB = 0l;
        int n = A.length;
        int ans[] = new int[n];

        for(int i=0; i<n; i++) {
            bitsA = bitsA | (1L << A[i]);
            bitsB = bitsB | (1L << B[i]);

            ans[i] = Long.bitCount(bitsA & bitsB);
        }

        return ans;
    }
}