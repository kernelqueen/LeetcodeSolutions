class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();

        // grouping elemnt
        // O(n) + O(n)
        HashMap<Integer, List<Integer>> hmap = new HashMap<>();
        // O(n)
        for(int i=0; i<n; i++) {
            if(!hmap.containsKey(nums[i]))
                hmap.put(nums[i], new ArrayList<>());
            hmap.get(nums[i]).add(i);
        }

        int precomputed[] = new int[n];
        Arrays.fill(precomputed, -1);

        for(List<Integer> list : hmap.values()) {
            // O(n)
            int len = list.size();

            if(len==1)
                continue;
            
            for(int i=0; i<len; i++) {
                
                int curr = list.get(i);
                int prev = list.get((i-1+len)%len); // i-1
                int next = list.get((i+1)%len); //i+1

                int dist1 = Math.abs(curr-prev); // n-dist1
                int dist2 = Math.abs(curr-next); // n-dist2

                
                precomputed[curr] = Math.min(Math.min(Math.min(dist1, dist2), n-dist1), n-dist2);

            }
            
        }

        for(int q : queries) {
            // O(Q)
            ans.add(precomputed[q]);
        }

        return ans;
    }
}