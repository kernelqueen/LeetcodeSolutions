class Solution {
    public int longestBalanced(int[] nums) {
        int maxlen = 0;
        // O(n)
        HashSet<Integer> even = new HashSet<>();
        HashSet<Integer> odd = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            // O(n^2)
            odd.clear();
            even.clear();
            for(int j=i; j<nums.length; j++) {
                if(nums[j]%2==0)
                    even.add(nums[j]);
                else odd.add(nums[j]);
                if(even.size()==odd.size()) {
                    maxlen = Math.max(maxlen, j-i+1);
                }
            }
        }

        return maxlen;
        
    }
}