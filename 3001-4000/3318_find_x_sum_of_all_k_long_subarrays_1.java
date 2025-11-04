class Solution {

    HashMap<Integer, Integer> hmap;

    public int[] findXSum(int[] nums, int k, int x) {
        hmap = new HashMap<>();

        int n = nums.length;

        int ans[] = new int[n-k+1];
        int index = 0;

        for(int i=0; i<n; i++) {
            // O(n)*(logk(x+k))
            // O(k)

            hmap.put(nums[i], hmap.getOrDefault(nums[i], 0) + 1);

            if(i >= k-1) {
                ans[index++] = getSum(x);

                int removenum = nums[i-k+1];
                hmap.put(removenum, hmap.get(removenum)-1);
                if(hmap.get(removenum)==0) hmap.remove(removenum);
            }

        }
        return ans;
    }

    int getSum(int x) {
        int sum = 0;
        // klogk 
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            {
                int f1 = hmap.get(a);
                int f2 = hmap.get(b);
                if(f1==f2) 
                    return b-a;
                return f2-f1;
            }
        });

        for(int key : hmap.keySet()) {
            pq.offer(key); 
        }
        // xlogk
        while(!pq.isEmpty() && x>0) {
            int top = pq.remove();
            sum += top*hmap.get(top);
            x--;
        }

        return sum;
    }
}