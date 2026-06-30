// ===================================================
// Solution 1: Sliding Window
// Time: O(n), Space: O(1)
// ===================================================
class Solution {
    public int numberOfSubstrings(String s) {
        int left = 0, right = 0, n = s.length();
        int[] count = new int[3]; // count[0]=a, count[1]=b, count[2]=c
        int ans = 0;

        while (right < n) {
            count[s.charAt(right) - 'a']++;

            // Shrink window from left while all three chars are present
            // Every valid window can extend to the right up to n-1, so add (n - right)
            while (isValid(count)) {
                ans += (n - right);
                count[s.charAt(left) - 'a']--;
                left++;
            }

            right++;
        }

        return ans;
    }

    boolean isValid(int[] count) {
        return count[0] > 0 && count[1] > 0 && count[2] > 0;
    }
}


// ===================================================
// Solution 2: Last Occurrence Tracking (Optimal)
// Time: O(n), Space: O(1)
// ===================================================
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] last = new int[3]; // last seen index of 'a', 'b', 'c'
        Arrays.fill(last, -1);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;

            // Earliest of the three last-seen indices = leftmost index needed
            // Any starting point in [0, minLast] produces a valid substring ending at i
            int minLast = Math.min(last[0], Math.min(last[1], last[2]));
            ans += (1 + minLast);
        }

        return ans;
    }
}