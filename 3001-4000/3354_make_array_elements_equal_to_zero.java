/*Simulation Approach */
class Solution {
    public int countValidSelections(int[] nums) {
        
        int count = 0;

        for(int i=0; i<nums.length; i++) {
            if(nums[i]==0 && isValid(i, nums,0)) {
                count++;
            }
            if(nums[i]==0 && isValid(i, nums, 1)) {
                count++;
            }
        }
        return count;

    }

    private boolean isValid(int curr, int inp[], int dir) {
        int[] nums = Arrays.copyOf(inp, inp.length);

        while(curr >=0 && curr<nums.length) {
            if(nums[curr]==0) {
                curr = dir==0 ? curr-1 : curr+1;
            }
            else {
                nums[curr]--;
                dir = dir==0? 1 : 0;
                curr = dir==0 ? curr-1 : curr+1;
            }
        }

        for(int num : nums) {
            if(num!=0)
                return false;
        }
        return true;

    }
}

/*Prefix Sum Approach */
class Solution {
    public int countValidSelections(int[] nums) {
        int ans = 0;
        int sum = 0;

        for(int num : nums ){
            sum +=  num;
        }

        int leftsum = 0;

        for(int i=0; i<nums.length; i++) {
            leftsum += nums[i];

            if(nums[i]==0) {
                if(leftsum==(sum-leftsum)) {
                    ans += 2;
                }
                else if(Math.abs(leftsum-(sum-leftsum))==1) {
                    ans +=1;
                }
            }
        }
        return ans;

        
    }
}















/*

 - Array elements are either 0 or more than 0
Valid selection ?
 nums[i] = 0
 direction - left or right
*/