/*Binary Search Solution */
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        // Sort the potions array to enable binary search
        Arrays.sort(potions);

        int n = spells.length;
        int ans[] = new int[n]; // Stores number of successful pairs for each spell

        // For each spell, find the minimum potion index that makes product >= success
        for (int i = 0; i < n; i++) {

            // Binary search to find the first potion strong enough
            int index = getMinPotion(spells[i], potions, success);

            // All potions from 'index' to end of array form successful pairs
            ans[i] = potions.length - index;
        }

        return ans;
    }

    // Finds the smallest index in 'potions' such that potions[index] * spell >= success
    // Returns potions.length if no such potion exists
    int getMinPotion(int spell, int[] potions, long success) {
        int left = 0, right = potions.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Cast to long to avoid overflow (since potions[i] * spell can exceed int range)
            if ((long) potions[mid] * spell >= success) {
                // This potion works, but check if there's a smaller index that also works
                right = mid - 1;
            } else {
                // Potion too weak → move to stronger ones (right side)
                left = mid + 1;
            }
        }

        // 'left' will point to the first valid potion index or potions.length if none valid
        return left;
    }
}


/*Two Pointer Solution */
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        // Sort potions in ascending order to enable two-pointer scanning
        Arrays.sort(potions);

        int n = spells.length;
        int[] ans = new int[n];

        // Create an index array to remember the original positions of spells
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Sort indices based on spell strength in ascending order
        // So we process weaker spells first, stronger spells later
        Arrays.sort(indices, (a, b) -> Integer.compare(spells[a], spells[b]));
        // Example:
        // spells = [3, 1, 2]
        // indices after sorting → [1, 2, 0]  (spell values 1, 2, 3)

        // Initialize pointer 'j' to the last potion (strongest)
        for (int i = 0, j = potions.length - 1; i < n; i++) {
            int si = indices[i];     // Original index of current spell
            int spell = spells[si];  // Spell strength

            // Move 'j' left while the potion is strong enough to achieve success
            // Once (potions[j] * spell) < success, stop (since potions are sorted ascending)
            while (j >= 0 && (long) potions[j] * spell >= success) {
                j--;
            }

            // After the loop:
            // All potions from (j + 1) to end of array will make a successful pair with this spell.
            // Count = total potions - (j + 1)
            ans[si] = potions.length - j - 1; // Store the result at the spell's original index
        }

        return ans;
    }
}


/*
Example walkthrough:
--------------------
spells = [3, 1, 2]
potions = [5, 8, 8]
success = 16

Sorted potions = [5, 8, 8]
Sorted spell indices = [1 (1), 2 (2), 0 (3)]  // ascending spell strength

Start j = 2 (strongest potion)

i = 0 → spell = 1
    8*1=8 <16 → stop
    ans[1] = 3 - 2 - 1 = 0  (no successful potions)

i = 1 → spell = 2
    8*2=16 >=16 → j=1
    8*2=16 >=16 → j=0
    5*2=10 <16 → stop
    ans[2] = 3 - 0 - 1 = 2 (successful potions 8,8)

i = 2 → spell = 3
    j=0 (5*3=15 <16) → stop
    ans[0] = 3 - 0 - 1 = 2 (successful potions 8,8)

Final output: [2, 0, 2]
--------------------

✅ Time Complexity:
   O(n log n + m log m + n + m)
   → Sorting both arrays + single pass through each

✅ Space Complexity:
   O(n) for indices array
*/
