class Solution {
    public long minTime(int[] wizard, int[] potions) {
        int n = wizard.length;
        int m = potions.length;

        long prev[] = new long[n]; // total time taken 
        
        prev[0] = potions[0] * wizard[0];

        for(int i=1; i<n; i++) {
            prev[i] = prev[i-1] + potions[0]*wizard[i];
        }

        // for each potion calculate and update prev array
        for(int i=1; i<m; i++) {
            // how much absolute time each wizard will take to brew this
            long timetaken[] = new long[n];

            long presum[] = new long[n];

            long maxtimetostart = prev[0];

            for(int j=0; j<n; j++) {
                int time = potions[i]*wizard[j];

                timetaken[j] = time;
                presum[j] = j==0 ? time : presum[j-1] + time;

                maxtimetostart = j==0 ? maxtimetostart : Math.max(maxtimetostart, prev[j]-presum[j-1]);
            }

            // updating the prev array - time taken in total
            prev[0] = maxtimetostart + timetaken[0];
            for(int j=1; j<n; j++) {
                prev[j] = prev[j-1] + timetaken[j];
            }
        }

        return prev[n-1];

    }
}
/*

potions = [5,1,4,2]



for potion 5, time taken = 5*1 + 5*5 + 2*5 +4*5 = 60 secs

maxtime = 5

*/