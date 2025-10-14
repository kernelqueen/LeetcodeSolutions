/*Suboptimal O(n*k) Approach */
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        
        int n = nums.size();
        HashSet<Integer> set = new HashSet<>(); // stores start indices of strictly increasing subarrays of length k

        for (int i = 0; i < n; i++) {
            // Try to find an increasing subarray starting at index i
            int j = i + 1;
            int prev = nums.get(i);

            // Expand the window as long as elements are strictly increasing
            // and subarray length < k
            while (j < n && j - i < k && nums.get(j) > prev) {
                prev = nums.get(j);
                j++;
            }

            // If we found a strictly increasing subarray of size k
            if (j - i == k) {
                // Check if there’s another increasing subarray ending exactly k elements before this one
                // i.e., the subarrays are non-overlapping but adjacent
                if (set.contains(i - k))
                    return true;

                // Store start index of this increasing subarray
                set.add(i);
            }
        }

        // If no two adjacent increasing subarrays of length k are found
        return false;
    }
}

/*Optimal O(n) Approach */
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        if (k == 1)
            return true; // every single element forms a valid subarray of length 1

        boolean[] temp = new boolean[n]; // marks indices where an increasing subarray of length k starts

        int left = 0, right = 1;
        // Example: nums = [2,5,7,8,9,2,3,4,3,1]
        // temp    = [T,T,T,F,F,T,F,F,F,F] (example representation)

        // Sliding window to find increasing segments
        while (right < n) {
            // Continue expanding as long as sequence is strictly increasing
            while (right < n && nums.get(right) > nums.get(right - 1)) {
                // When current window length reaches k, mark its start
                if (right - left + 1 == k) {
                    temp[left++] = true; // mark start of valid subarray
                }
                right++;
            }
            // Reset pointers when increase breaks
            left = right;
            right++;
        }

        // Check if there exist two valid increasing subarrays
        // whose starting indices differ by exactly k (adjacent)
        for (int i = k; i < n; i++) {
            if (temp[i] && temp[i - k])
                return true;
        }

        return false;
    }
}
