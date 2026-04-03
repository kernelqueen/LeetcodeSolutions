class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        // -1 .... +inf
        int r[][] = new int[n+2][2];
        for(int i=0; i<n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }
        r[n][0] = -1;
        r[n][1] = 0;
        r[n+1][0] = Integer.MAX_VALUE;
        r[n+1][1] = 0;
        // O(nlogn + mlogn)
        // O(n)
        Arrays.sort(r, (a,b)->Integer.compare(a[0],b[0]));
        Arrays.sort(walls);

        int LL=0, LR=1, RL=2, RR=3;
        int dp[] = new int[4];
        int leftrobot = 0;
        int rightrobot = 1;
        
        for(int wall : walls) {

            while(wall > r[rightrobot][0]) {
                leftrobot++;
                rightrobot++;

                // dp state
                int maxL = Math.max(dp[LL], dp[RL]);
                int maxR = Math.max(dp[LR], dp[RR]);

                dp[LL] = maxL;
                dp[LR] = maxL;

                
                dp[RL] = maxR;
                dp[RR] = maxR;
            }

            int r1reach = r[leftrobot][0] + r[leftrobot][1];
            int r2reach = r[rightrobot][0] - r[rightrobot][1];

            boolean canR1 = wall <= r1reach; 
            boolean canR2 = wall >= r2reach;

            boolean isRightPoint = wall==r[rightrobot][0];

            // state LL 
            
            if(canR2)
                dp[LL]++;

            // state LR
            if(isRightPoint)
                dp[LR]++;
            // state RL
            if(canR1 || canR2)
                dp[RL]++;
            // state RR
            if(canR1 || isRightPoint)
                dp[RR]++;
        }
        int ans = 0;
        for(int v : dp) {
            ans = Math.max(v, ans);
        }

        return ans;


    }
}