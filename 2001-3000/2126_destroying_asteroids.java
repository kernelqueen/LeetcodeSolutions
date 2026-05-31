
/************************* O(nlogn) Sorting Solution************************ */
class Solution {
    public boolean asteroidsDestroyed(int m, int[] nums) {
        Arrays.sort(nums);
        long mass = m;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] <= mass)
                mass += nums[i];
            else return false;
        }
        return true;
    }
}
/************************ O(n) using counting sort************************ */
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] nums) {
        
        long arr[] = new long[100001];

        for(int num : nums) {
            arr[num] ++;
        }

        long m = mass;

        for(int i=1; i<arr.length; i++) {
            if(arr[i] > 0) {
                if(m >= i) {
                    m+=(i*arr[i]);
                } else return false;
                    
            } 
        }
        return true;
    }
}