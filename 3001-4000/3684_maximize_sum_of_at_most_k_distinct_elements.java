class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();

        Set<Integer> temp = new HashSet<>();

        for(int i=nums.length-1; i>=0; i--) {
            if(temp.size() == k) {
                break;
            }

            if(temp.contains(nums[i])) {
                continue;
            }
            else {
                list.add(nums[i]);
                temp.add(nums[i]);
            }
        }

        int index = 0;
        int ans[] = new int[list.size()];

        for(int num : list) {
            ans[index++] = num;
        }

        return ans;
    }
}