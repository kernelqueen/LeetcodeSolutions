class Solution {
    public int earliestTime(int[][] tasks) {

        int earliest = Integer.MAX_VALUE;
        
        for(int task[] : tasks) {
            int start = task[0];
            int time = task[1];
            earliest = Math.min(start+time, earliest);
        }
        return earliest;

    }
}