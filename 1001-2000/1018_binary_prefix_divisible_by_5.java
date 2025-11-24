class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        
        List<Boolean> ans = new ArrayList<>(); //O(n)

        int curr = 0;

        for(int num : nums) {
            //O(n)

            int newnum = (curr<<1 + num);
            if(newnum%5==0) {
                ans.add(true);
            }
            else ans.add(false);
            curr = newnum%5;
        }

        return ans;

    }
}