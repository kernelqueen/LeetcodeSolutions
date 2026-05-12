class Solution {
    public int minimumEffort(int[][] tasks) {
        
        int ans = 0;
        int curr = 0;
        // O(nlogn)
        Arrays.sort(tasks, (a,b)-> {
            int t1 = a[1]-a[0];
            int t2 = b[1]-b[0];
            return t2-t1;
        });

        for(int i=0; i<tasks.length; i++) {
            if(tasks[i][1] > curr) {
                ans += (tasks[i][1] - curr);
                curr = tasks[i][1];
            }
            curr = curr - tasks[i][0];
        }

        return ans;

    }
}