class Solution {
    public int maxArea(int[] height) {
        int n = height.length; 

        int left=0, right=n-1;

        int max = 0;

        while(left < right) {

            int min = Math.min(height[left], height[right]);

            int currWater = min * (right-left);

            max = Math.max(currWater, max);

            while(left<right && height[left] <= min) {
                left++;
            }

            while(left<right && height[right] <= min) {
                right--;
            }

        }

        return max;


    }
}