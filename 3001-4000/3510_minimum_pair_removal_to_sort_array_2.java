class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        long val[] = new long[n];
        for(int i=0; i<n; i++) {
            val[i] = nums[i];
        }

        int left[] = new int[n];
        int right[] = new int[n];
        // O(n)
        for(int i=0; i<n; i++) {
            left[i] = i-1;
            right[i] = i+1;
        }

        // [sum, index]
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0]==b[0]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[0], b[0]);
        });

        int unsorted = 0;
        for(int i=0; i<n-1; i++) {
            pq.offer(new long[]{val[i]+val[i+1], i});
            if(val[i]>val[i+1])
                unsorted++;
        }
        int ans = 0;
        // O(n).logn = O(nlogn)
        // O(n)
        while(unsorted>0 && !pq.isEmpty()) {
            long curr[] = pq.poll();
            int i = (int)curr[1];
            int j = right[i];
            long sum = curr[0]; // current sum of this pair

            // lazy removal
            if(j>=n || left[j]!=i || val[i]+val[j]!=sum )
                continue;
            
            // valid pair

            if(val[i] > val[j]) {
                unsorted--;
            }

            //[prev, i, j, next]
            int prev = left[i];
            int next = right[j];
            if(prev!=-1 && val[prev] > val[i]) {
                unsorted--;
            }

            if(next!=n && val[j] > val[next])
                unsorted--;
            
            // merging
            //[prev, i, j, next]
            val[i] = sum;
            right[i] = next;
            if(next!=n) {
                left[next] = i;
            }

            ans++;

            // [prev, i, _ , next]

            if(prev!=-1) {
                if(val[prev] > val[i]) 
                    unsorted++;
                pq.offer(new long[]{val[prev]+val[i], prev});
            }

            if(next!=n) {
                if(val[i] > val[next]) {
                    unsorted++;
                }
                pq.offer(new long[]{val[i]+val[next], i});
            }
            
        }

        return ans;
    }
}