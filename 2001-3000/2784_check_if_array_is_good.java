class Solution {
    public boolean isGood(int[] nums) {
        int max = 0;
        int freq[] = new int[nums.length];
        
        for(int num : nums) {
            max = Math.max(max, num);
            if(num < nums.length)
                freq[num]++;
        }

        if(nums.length!=max+1)
            return false;

        if(freq[max] !=2)
            return false;
       
        for(int i=1; i<freq.length; i++) {
            if(freq[i]==0)
                return false;
        }
        return true;
        
    }
}