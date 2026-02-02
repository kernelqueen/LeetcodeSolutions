class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        TreeSet<Integer> currset =
    new TreeSet<>((a,b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);

TreeSet<Integer> futset =
    new TreeSet<>((a,b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);

        long cost = Long.MAX_VALUE;
        int n = nums.length;
        long currsum = 0l;

        for(int i=1; i<=dist+1; i++) {
            currset.add(i);
            currsum += nums[i];
        }
        while(currset.size() > k-1) {
            // removing max values and adding to futset
            int ind = currset.pollLast();
            currsum -=nums[ind];
            futset.add(ind);
        }
        cost = Math.min(currsum, cost);
        // need k-1 lowest numbers with window size = dist+1
        for(int i=1; i+dist+1 < n ; i++) {
            int rightInd = i+dist+1;
            if(currset.contains(i)) {
                currsum -= nums[i];
                currset.remove(i);
                currsum += nums[rightInd];
                currset.add(rightInd);
            }
            else {
                futset.remove(i);
                futset.add(rightInd);
            }
            
            if(futset.size() > 0) {
                int minF = futset.first();
                int maxC = currset.last();
                if(nums[minF] < nums[maxC]) {
                    currsum -= nums[maxC];
                    currset.remove(maxC);
                    futset.add(maxC);
                    currsum += nums[minF];
                    currset.add(minF);
                    futset.remove(minF);
                }
            }
            cost = Math.min(cost, currsum); 
        }
        return cost + nums[0];
    }
}