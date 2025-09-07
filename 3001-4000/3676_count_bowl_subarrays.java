class Solution {
    public long bowlSubarrays(int[] nums) {
        
        int n = nums.length;

        // Arrays to store nearest greater element index on left and right side
        int left[] = new int[n];
        int right[] = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Step 1: Find nearest greater element on the LEFT for each index
        for(int i=0; i<n; i++) {
            // Pop smaller elements since they cannot be nearest greater
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i])
                stack.pop();

            // If stack is empty, no greater element exists on left → -1
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            
            // Push current index onto stack
            stack.push(i);

            // Example: left[] = [-1, -1, 1, 2, 1]
        }

        // Clear stack for re-use
        stack.clear();

        // Step 2: Find nearest greater element on the RIGHT for each index
        for (int i = n - 1; i >= 0; i--) {
            // Pop smaller elements since they cannot be nearest greater
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }

            // If stack is empty, no greater element exists on right → -1
            right[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current index onto stack
            stack.push(i);
        }

        long count = 0;

        // Step 3: Count indices that have both a left and right greater element
        // These positions form the "bottom" of a valid bowl subarray
        for(int i=0; i<n; i++) {
            if(left[i] != -1 && right[i] != -1) {
                count++;
            }
        }

        // Return total number of bowl subarrays
        return count;
    }
}
