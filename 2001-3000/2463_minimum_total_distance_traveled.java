/***************Recursive + Memoization***************** */
class Solution {
    Long dp[][];
    // m, n = m*n
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        dp = new Long[robot.size()][factory.length];
        // sort
        Collections.sort(robot);
        Arrays.sort(factory, (a,b)->a[0]-b[0]);
        return helper(robot, factory, 0, 0);
    }

    long helper(List<Integer> robot, int[][] factory, int rindex, int findex) {
        // base case
        if(rindex==robot.size())
            return 0;
        if(findex==factory.length)
            return Long.MAX_VALUE;

        if(dp[rindex][findex]!=null)
            return dp[rindex][findex];
        
        // skip factory
        long skip = helper(robot, factory, rindex, findex+1);

        // take current factory
        long dist = 0;
        long ans = Long.MAX_VALUE;
        for(int k=0; k<factory[findex][1] && rindex+k<robot.size(); k++) {
            dist += Math.abs(robot.get(rindex+k)-factory[findex][0]);
            long next = helper(robot, factory, rindex+k+1, findex+1);
            if(next < Long.MAX_VALUE)
                ans = Math.min(ans, dist+next);
        }

        return dp[rindex][findex] = Math.min(skip, ans);
    }
}


/****************Iterative DP Solution***************** */
class Solution {

public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
    Collections.sort(robot);                          
    Arrays.sort(factory, (a, b) -> a[0] - b[0]);     

    long[][] dp = new long[robot.size() + 1][factory.length + 1];

    for (int ri = 0; ri <= robot.size(); ri++)
        Arrays.fill(dp[ri], Long.MAX_VALUE);

    for (int fi = 0; fi <= factory.length; fi++)
        dp[robot.size()][fi] = 0;

    for (int ri = robot.size() - 1; ri >= 0; ri--) {
        for (int fi = factory.length - 1; fi >= 0; fi--) {

            long skip = dp[ri][fi + 1];

            long dist = 0;
            long ans = Long.MAX_VALUE;
            for (int k = 0; k < factory[fi][1] && ri + k < robot.size(); k++) {
                dist += Math.abs(robot.get(ri + k) - factory[fi][0]);
                long next = dp[ri + k + 1][fi + 1];
                if (next < Long.MAX_VALUE)
                    ans = Math.min(ans, dist + next);
            }

            dp[ri][fi] = Math.min(skip, ans);
        }
    }

    return dp[0][0];
}

    
}