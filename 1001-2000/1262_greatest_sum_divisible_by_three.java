/************Greedy Approach******** */
class Solution {
    public int maxSumDivThree(int[] nums) {
        
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        if(sum%3==0) {
            return sum;
        }

        Arrays.sort(nums);

        // rem == 1, rem == 2
        int firstrem1 = findFirst(nums, 1, 0);
        int secrem1 = findFirst(nums, 1, firstrem1+1);
        int firstrem2 = findFirst(nums, 2, 0);
        int secrem2 = findFirst(nums, 2, firstrem2+1);

        if(sum%3==1) {
            int opt1 = (firstrem1==-1) ? 0 : sum-nums[firstrem1];
            int opt2 = (firstrem2==-1 || secrem2==-1) ? 0 : sum - nums[secrem2] - nums[firstrem2];
            return Math.max(opt1, opt2);
        } else {
            int opt1 = (firstrem2==-1) ? 0 : sum-nums[firstrem2];
            int opt2 = (firstrem1==-1 || secrem1==-1) ? 0 : sum - nums[secrem1] - nums[firstrem1];
            return Math.max(opt1, opt2);
        }


    }

    int findFirst(int nums[], int rem, int index) {
        for(int i=index; i<nums.length; i++) {
            if(nums[i]%3==rem)
                return i;
        }
        return -1;
    }
}

/*DP Approach */
class Solution {
    public int maxSumDivThree(int[] nums) {
        int dp[] = new int[3];

        for(int num : nums) {
            int next[] = dp.clone();
            for(int curr : dp) {
                int sum = curr + num;
                int index = sum%3;
                next[index] = Math.max(next[index], sum);
            }

            dp = next;
        }

        return dp[0];
    }
}