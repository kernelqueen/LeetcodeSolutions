class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums); // O(nlogn)

        int n = nums.length;

        for(int i=n-3; i>=0; i--) {

            if(nums[i] + nums[i+1] > nums[i+2]) {
                return nums[i] + nums[i+1] + nums[i+2];
            }

        }

        return 0;
    }
}

/*

Condition for three sides to form a triangle?

sum of any two sides  > third side

a,b,c sides of a triangle

a+b > c, b+c > a, a+c>b

But the minimal condition?

sum of two smaller side  >  the third larger side

[2,2,3]

Example:
[1,2,3,4,5,6,7,8,8,9,9,11,20]
*/