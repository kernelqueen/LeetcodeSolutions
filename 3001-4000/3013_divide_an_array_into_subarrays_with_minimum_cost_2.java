class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        // O(n)
        TreeSet<Integer> currset = new TreeSet<>((a,b)-> nums[a]!=nums[b] ? nums[a]-nums[b] : a-b);
        TreeSet<Integer> futset = new TreeSet<>((a,b)-> nums[a]!=nums[b] ? nums[a]-nums[b] : a-b);
        long cost = Long.MAX_VALUE;

        int n = nums.length;
        long currsum = 0l; // sum of cost of all subarays in a wind

        // populate currset and futset
        // currser = top k-1 min elements
        // futset = rem elements

        for(int i=1; i<=dist+1; i++) {
            currset.add(i);
            currsum += nums[i];
        }

        while(currset.size() > k-1) {
            int ind = currset.pollLast();
            currsum -= nums[ind];
            futset.add(ind);
        }

        cost = currsum;
        // O(n*logn)
        for(int i=1; i < n-1-dist; i++) {
            int rightInd = i+dist+1;
            int leftInd = i;
            if(currset.contains(leftInd)) {
                currsum -= nums[leftInd];
                currset.remove(leftInd);
                currsum += nums[rightInd];
                currset.add(rightInd);
            }
            else {
                futset.remove(leftInd);
                futset.add(rightInd);
            }

            // balancing
            if(futset.size() > 0) {
                int minF = futset.first();
                int maxC = currset.last();
                if(nums[minF] < nums[maxC]) {
                    currsum -= nums[maxC];
                    currset.remove(maxC);
                    currset.add(minF);
                    currsum += nums[minF];
                    futset.remove(minF);
                    futset.add(maxC);
                }
            }
            
            
            cost = Math.min(cost, currsum);
        }

        return cost + nums[0];
    
    }
}