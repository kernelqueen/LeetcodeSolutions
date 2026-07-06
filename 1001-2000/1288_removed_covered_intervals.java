class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        // O(nlogn), O(1)
        Arrays.sort(intervals, (a,b)-> {
            if(a[0]==b[0])
                return b[1]-a[1];
            return a[0]-b[0];
        });

        int remove = 0;
        int prev = 0;
        for(int i=1; i<n; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            int pstart = intervals[prev][0], pend = intervals[prev][1];

            if(start>=pstart && end<=pend)
                remove++;
            else prev = i;
        }

        return n-remove;
    }
}