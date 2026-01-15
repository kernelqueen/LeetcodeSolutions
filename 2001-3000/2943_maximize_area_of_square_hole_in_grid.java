class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hbars, int[] vbars) {
        // k, klogk
    
        // sort
        Arrays.sort(hbars);
        Arrays.sort(vbars);

        int hstrip = 1, vstrip = 1;

        int currstrip = 1;
        int prevbar = hbars[0];

        for(int i=1; i<hbars.length; i++) {
            if(hbars[i] == prevbar+1) {
                currstrip++;
                hstrip = Math.max(currstrip, hstrip);
            } else {
                currstrip = 1;
            }
            prevbar = hbars[i];
        }

        // vertical stripe calculation
        prevbar = vbars[0];
        currstrip = 1;
        for(int i=1; i<vbars.length; i++) {
            if(vbars[i] == prevbar+1) {
                currstrip++;
                vstrip = Math.max(currstrip, vstrip);
            } else {
                currstrip = 1;
            }
            prevbar = vbars[i];
        }

        // hstrip, vstrip 

        int min = Math.min(hstrip, vstrip)+1;
        return min*min;
        

    }
}