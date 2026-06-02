class Solution {

    public int earliestFinishTime(int[] landStart, int[] landDur,
                                  int[] waterStart, int[] waterDur) {

        // O(n + m)
        int bestLandFinish = Integer.MAX_VALUE;
        int bestWaterFinish = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        // Earliest possible finish among all land rides.
        for (int i = 0; i < landStart.length; i++) {
            bestLandFinish = Math.min(bestLandFinish, landStart[i] + landDur[i]);
        }

        // Try every water ride after the earliest-finishing land ride.
        for (int i = 0; i < waterStart.length; i++) {
            int finishTime = Math.max(bestLandFinish, waterStart[i]) + waterDur[i];
            ans = Math.min(ans, finishTime);
        }

        // Earliest possible finish among all water rides.
        for (int i = 0; i < waterStart.length; i++) {
            bestWaterFinish = Math.min(bestWaterFinish, waterStart[i] + waterDur[i]);
        }

        // Try every land ride after the earliest-finishing water ride.
        for (int i = 0; i < landStart.length; i++) {
            int finishTime = Math.max(bestWaterFinish, landStart[i]) + landDur[i];
            ans = Math.min(ans, finishTime);
        }

        return ans;
    }
}