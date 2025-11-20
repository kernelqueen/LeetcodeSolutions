class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        HashSet<Integer> set = new HashSet<>(); // O(n)
        Arrays.sort(intervals, (a,b)->Integer.compare(a[1], b[1])); // Onlogn
        int max = intervals[0][1];
        int secmax = max-1; // [2,5] --> 4,5
        set.add(max);
        set.add(secmax);

        for(int i=1; i<intervals.length; i++) {

            // if complete overlap

            if(intervals[i][0] <= secmax) {
                continue;
            }

            else if(intervals[i][0] <= max) {
                secmax = max;
                max = intervals[i][1];
                if(set.contains(max)) {
                    set.add(max-1);
                    secmax = max-1;
                }
                set.add(max);
            }

            else {
                // mutual exlcusive

                max = intervals[i][1];
                secmax = max-1;
                set.add(max);
                set.add(secmax);
            }

            //System.out.println(set);
        }

        return set.size();
    }
}