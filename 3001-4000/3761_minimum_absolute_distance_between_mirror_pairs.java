class Solution {
    public int minMirrorPairDistance(int[] nums) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        int n = nums.length;
        int min = n+1;
        // O(n)
        // O(n)
        for(int i=0; i<nums.length; i++) {
            if(hmap.containsKey(nums[i]))
                min = Math.min(min, Math.abs(i-hmap.get(nums[i])));
            int mirror = getReverse(nums[i]);
            hmap.put(mirror, i);
        }
        
        return min==n+1 ? -1 : min;
    }

    int getReverse(int num) {
        int ans = 0;
        while(num > 0) {
            ans = ans*10 + num%10;
            num = num/10;
        }
        return ans;
    }
}