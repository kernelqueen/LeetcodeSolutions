// ===================================================
// Solution 1: Sorting
// Time: O(n log n), Space: O(1)
// ===================================================
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;

        // First element must be 1 (can only decrement, not increment)
        if (arr[0] != 1) arr[0] = 1;

        // Each element can be at most 1 more than the previous
        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) <= 1) continue;
            arr[i] = arr[i - 1] + 1;
        }

        return arr[n - 1];
    }
}


// ===================================================
// Solution 2: Frequency Count (Optimal)
// Time: O(n), Space: O(n)
// ===================================================
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;

        // Values larger than n can always be decremented to fit within [1, n]
        // so cap all frequencies at index n
        int[] count = new int[n + 1];
        for (int a : arr) count[Math.min(a, n)]++;

        // Greedily assign the highest valid value at each step
        // ans tracks the max value achievable so far (starts at 1 for first element)
        int ans = 1;
        for (int i = 2; i < count.length; i++) {
            // count[i] elements available at value i — each can extend ans by 1
            int next = ans + count[i];
            // Can't exceed i itself (can only decrement, not increment)
            ans = Math.min(i, next);
        }

        return ans;
    }
}