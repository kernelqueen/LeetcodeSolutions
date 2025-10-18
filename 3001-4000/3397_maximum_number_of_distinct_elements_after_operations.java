class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        // Sort the array so we can process numbers in increasing order
        // This helps to avoid overlapping ranges and to maximize distinct elements
        Arrays.sort(nums); // O(n log n)
        
        int count = 1; // At least one distinct element can always be formed
        
        // Start with the smallest possible value for the first element
        int prev = nums[0] - k;

        // Iterate through the rest of the array
        for (int i = 1; i < nums.length; i++) { // O(n)
            int min = nums[i] - k; // Minimum possible value for current number
            int max = nums[i] + k; // Maximum possible value for current number

            if (min > prev) {
                // If the current range starts after 'prev',
                // we can safely take the smallest value (min) to keep distinctness
                count++;
                prev = min;
            } 
            else if (prev < max) {
                // If 'prev' lies within the current range,
                // we can increment it by 1 to stay distinct but still valid
                prev = prev + 1;
                count++;
            } 
            else {
                // If even the maximum allowed value is <= prev,
                // we can’t make a distinct element — skip this number
                continue;
            }
        }

        // Return total distinct numbers we could form
        return count;
    }
}


/*
-----------------------------------
Concept Explanation:
-----------------------------------

We can modify each number within the range [num - k, num + k].

Goal:
→ Maximize the count of distinct elements after performing valid changes.

Approach:
1. Sort the array — ensures we handle numbers in increasing order.
2. Keep track of the last chosen value (`prev`).
3. For each number:
   - If its allowed range starts *after* `prev`, choose the smallest possible value (min).
   - If its range *overlaps* with `prev`, move to `prev + 1` (next distinct number).
   - If its entire range is already covered (max <= prev), skip it.
4. Count how many distinct numbers we successfully assigned.

Example:
nums = [4, 4, 4, 4], k = 1
Possible ranges: [3–5], [3–5], [3–5], [3–5]

Steps:
prev = 3
→ next (min=3, max=5) → prev=4 (distinct)
→ next (min=3, max=5) → prev=5 (distinct)
→ next (min=3, max=5) → all ≤ prev → skip

Total distinct = 3

-----------------------------------
Time Complexity:  O(n log n)
Space Complexity: O(1)
-----------------------------------
*/
