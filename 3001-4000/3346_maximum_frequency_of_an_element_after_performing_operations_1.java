class Solution {
    public int maxFrequency(int[] nums, int k, int ops) {
        Arrays.sort(nums); // O(nlogn)

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int ans = 0; 
         //[1,2,3,4,5] //[1,100000]
        // Iterate over all possible target values from smallest to largest
        // O(n)
        for (int i = nums[0]; i <= nums[nums.length - 1]; i++) {
            // Find the range of elements that can be changed to i using binary search
            int a = lowerBound(nums, i - k); // First index where element >= i - k
            int b = upperBound(nums, i + k) - 1; // Last index where element <= i + k

            // Compute possible maximum frequency for element i
            int currentFreq = freq.getOrDefault(i, 0);
            int rangeCount = (b - a + 1) - currentFreq;
            ans = Math.max(ans, currentFreq + Math.min(rangeCount, ops));
        }

        return ans;
    }

    // returns first index where nums[idx] >= target
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // returns first index where nums[idx] > target
    private int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}

/*

What can we do--

- Sort the array
- Get frequencies of each element in the array

Try to see if you can achieve ans in the given range
Ex - [1,4,5] --> for 1 check 1-k to 1+k numbers in the array
[1,2,3,4,5]
these are the numbers you can convert
Get the count of all the numbers that you can change within this range. 
- 

*/