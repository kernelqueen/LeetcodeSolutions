class Solution {
    public int minNumberOperations(int[] target) {
        
        int count = 0; // Tracks total number of operations required
        int prev = 0;  // Stores the previous element value for comparison

        // Iterate through each number in the target array
        for (int num : target) {
            // If current number is greater than the previous one,
            // we need (num - prev) additional operations to reach this height
            if (num > prev) {
                count += num - prev;
            }
            prev = num;
        }

        return count;
    }
}
