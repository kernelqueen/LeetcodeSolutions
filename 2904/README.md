# Shortest Beautiful Substring

## Problem

You are given a binary string `s` and a positive integer `k`.

A substring is called **beautiful** if it contains exactly `k` occurrences of `'1'`.

Return the **shortest beautiful substring**.

If multiple beautiful substrings have the same minimum length, return the **lexicographically smallest** one.

If no beautiful substring exists, return an empty string `""`.

---

## Examples

### Example 1

```text
Input:
s = "100011001"
k = 3

Output:
"11001"
```

### Example 2

```text
Input:
s = "1011"
k = 2

Output:
"11"
```

### Example 3

```text
Input:
s = "000"
k = 1

Output:
""
```

---

## Pattern

### Sliding Window / Two Pointers

The main pattern used in this problem is:

> **Exactly `k` occurrences + shortest substring → Sliding Window**

Since the string is binary, we only need to keep track of the number of `'1'` characters inside the current window.

We maintain two pointers:

```text
left
 ↓
[ current window ]
              ↑
            right
```

- `right` expands the window.
- `left` shrinks the window when there are more than `k` ones.
- When the window contains exactly `k` ones, it is a beautiful substring.

---

## Key Observation

Suppose the current window contains exactly `k` ones:

```text
000110010
^^
leading zeros
```

The leading zeros are unnecessary because removing them does not change the number of ones.

Therefore, whenever we have exactly `k` ones, we greedily remove leading zeros:

```java
while (s.charAt(left) == '0') {
    left++;
}
```

This gives the shortest valid window ending at the current `right`.

---

## Approach

### 1. Initialize the Sliding Window

```java
int left = 0;
int ones = 0;
```

`ones` stores the number of `'1'` characters currently present in the window.

---

### 2. Expand the Window

Move `right` from left to right.

Whenever we encounter `'1'`:

```java
if (s.charAt(right) == '1') {
    ones++;
}
```

---

### 3. Shrink When There Are More Than `k` Ones

If:

```java
ones > k
```

the current window is invalid.

Move `left` forward until the window contains at most `k` ones.

```java
while (ones > k) {
    if (s.charAt(left) == '1') {
        ones--;
    }

    left++;
}
```

---

### 4. Process a Beautiful Substring

When:

```java
ones == k
```

the current window is beautiful.

Remove unnecessary leading zeros:

```java
while (s.charAt(left) == '0') {
    left++;
}
```

Now calculate its length:

```java
int len = right - left + 1;
```

---

### 5. Find the Shortest Substring

Maintain:

```java
int minLen = Integer.MAX_VALUE;
String ans = "";
```

If the current substring is shorter, update the answer.

If both substrings have the same length, compare them lexicographically.

```java
if (len < minLen ||
    (len == minLen && candidate.compareTo(ans) < 0)) {

    minLen = len;
    ans = candidate;
}
```

---

## Java Implementation

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        // Left pointer of the sliding window
        int left = 0;

        // Number of '1's in the current window
        int ones = 0;

        // Length of the shortest beautiful substring found
        int minLen = Integer.MAX_VALUE;

        // Final answer
        String ans = "";

        // Expand the window using right pointer
        for (int right = 0; right < n; right++) {

            // Add current character to the window
            if (s.charAt(right) == '1') {
                ones++;
            }

            /*
             * If the window contains more than k ones,
             * shrink it from the left.
             */
            while (ones > k) {

                // Remove s[left] from the window
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            /*
             * Exactly k ones means we have
             * a beautiful substring.
             */
            if (ones == k) {

                /*
                 * Remove unnecessary leading zeros.
                 *
                 * They do not affect the number of ones,
                 * so removing them makes the substring shorter.
                 */
                while (s.charAt(left) == '0') {
                    left++;
                }

                // Length of the current beautiful substring
                int len = right - left + 1;

                // Create the current candidate
                String candidate = s.substring(left, right + 1);

                /*
                 * Update the answer if:
                 *
                 * 1. Current candidate is shorter, OR
                 * 2. Both have the same length and current
                 *    candidate is lexicographically smaller.
                 */
                if (len < minLen ||
                    (len == minLen && candidate.compareTo(ans) < 0)) {

                    minLen = len;
                    ans = candidate;
                }
            }
        }

        // If no beautiful substring exists, ans remains ""
        return ans;
    }
}
```

---

## Dry Run

Consider:

```text
s = "1011"
k = 2
```

### Step 1

```text
window = "1"
ones = 1
```

Not beautiful because `ones < k`.

### Step 2

```text
window = "10"
ones = 1
```

Still not beautiful.

### Step 3

```text
window = "101"
ones = 2
```

Now we have exactly `k = 2` ones.

Candidate:

```text
"101"
length = 3
```

So:

```text
ans = "101"
```

### Step 4

Add the final `'1'`:

```text
window = "1011"
ones = 3
```

Since:

```text
ones > k
```

move `left`.

After removing the first `'1'`:

```text
window = "011"
ones = 2
```

Remove the unnecessary leading zero:

```text
window = "11"
```

Now:

```text
length = 2
```

Since `2 < 3`:

```text
ans = "11"
```

Final answer:

```text
"11"
```

---

## Why BFS Is Not Required

BFS is not suitable here because this is not a graph/state traversal problem.

There are no:

- Nodes
- Edges
- Levels
- Graph states

Instead, we are working with a **contiguous substring** and maintaining a condition:

```text
number of 1s == k
```

This strongly suggests:

```text
Substring
    +
Exactly k occurrences
    +
Minimum length
        ↓
Sliding Window
```

Then:

```text
Same minimum length
        ↓
Lexicographical comparison
```

---

## Pattern Recognition

Whenever you see:

```text
Find a shortest/longest substring
+
Maintain some count/property
+
Condition becomes valid/invalid
```

Think about:

> **Sliding Window / Two Pointers**

For this problem, the complete pattern is:

> **Sliding Window + Greedy Shrinking + Lexicographical Tie-Breaking**

---

## Complexity

Let `n = s.length()`.

### Time Complexity

```text
O(n²)
```

The sliding-window traversal is linear, but creating and comparing candidate substrings can take `O(n)`.

Since:

```text
n <= 100
```

this is easily fast enough.

### Space Complexity

```text
O(n)
```

for storing the candidate substring.

---

## Key Takeaway

Remember this pattern:

```text
Exactly k occurrences
        ↓
Sliding Window

Shortest valid substring
        ↓
Greedily shrink from left

Multiple answers of same length
        ↓
Lexicographical comparison
```

**Final Pattern:**

```text
Sliding Window
+ Two Pointers
+ Greedy Shrinking
+ Lexicographical Tie-Breaking
```
