class Solution {
    public int[] separateDigits(int[] nums) {
        
        List<Integer> list = new ArrayList<>();

        // O(n.log(m))
        // O(n*logM)

        for(int i=nums.length-1; i>=0; i--) {
            int num = nums[i];
            while(num > 0) {
                list.add(num%10);
                num = num/10;
            }
        }
        
        int ans[] = new int[list.size()];
        int ind = 0;
        for(int i=list.size()-1; i>=0; i--) {
            ans[ind++] = list.get(i);
        }

        return ans;

    }
}

// [7,7,3,8,5,2,3,1]