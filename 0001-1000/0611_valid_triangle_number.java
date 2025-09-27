class Solution {
    public int triangleNumber(int[] nums) {
        
        Arrays.sort(nums);

        int n = nums.length;

        int count = 0;

        for(int i=n-1; i>=0; i--) {
            int left = 0, right = i-1;
            while(left < right) {
                if(nums[left] + nums[right] > nums[i]) {
                    count += right-left;
                    right--;
                }
                else left++;
            }
        }

        return count;

    }
}

// O(n^3) ~=10^9 TLE

/*

Triangle sides = [4,4,3]
   /\
  /  \
 /    \
/_ _ _ \


condition for three sides to form a triangle: 
sum of any two sides > third side

Minimal condition:

sum of two smallest sides > largest side
[2,2,3]

*/