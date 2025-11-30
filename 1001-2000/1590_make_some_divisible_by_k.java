class Solution {
    public int minSubarray(int[] nums, int p) {
        
        int n = nums.length;

        // base case
        // sum%p = 0 // return 0

        int sum = 0;
        for(int num : nums) {
            sum = (sum + num)%p;
        }

        int rem = sum%p;
        if(rem==0)
            return 0;
        
        // prefix[L]%p = prefix[R]%p - rem;

        HashMap<Integer, Integer> hmap = new HashMap<>();

        hmap.put(0, -1);

        int currsum = 0;
        int minlen = nums.length;

        for(int i=0; i<nums.length; i++) {
            currsum = (currsum + nums[i])%p;

            // prefix[R]%p - rem;
            int val = (currsum - rem + p)%p;

            if(hmap.containsKey(val)) {
                minlen = Math.min(minlen, i-hmap.get(val));
            }

            hmap.put(currsum, i);
            
        }

        return minlen==nums.length ? -1 : minlen;


    }
}