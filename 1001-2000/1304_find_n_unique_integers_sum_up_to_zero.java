class Solution {
    public int[] sumZero(int n) {
        int ans[] = new int[n];

        // If n is odd, place a 0 in the middle
        if (n % 2 != 0) {
            ans[n / 2] = 0;
        }

        int val = 1, i = 0, j = n - 1;
        while (i < j) {
            ans[i++] = val;    // Place positive value on the left
            ans[j--] = -val;   // Place negative value on the right
            val++;             // Increment value for the next pair
        }

        return ans;
    }
}

/*
Examples:

For n = 3 → [1, 0, -1] → sum = 0
For n = 2 → [1, -1]    → sum = 0
For n = 5 → [1, 2, 0, -2, -1] → sum = 0
*/
