class Solution {
    String result;

    public String findLexSmallestString(String s, int a, int b) {
        result = s;
        helper(s, a, b, new HashSet<>());
        return result;
    }

    /**
     * Recursive DFS to explore all possible transformations.
     * At each step:
     *  1. Add 'a' to digits at odd indices (mod 10).
     *  2. Rotate the string to the right by 'b' positions.
     *
     * 'seen' ensures we don't revisit the same configuration.
     */
    void helper(String s, int a, int b, HashSet<String> seen) {
        if (seen.add(s)) { // process only if string not seen before
            // Update result if current string is lexicographically smaller
            if (result.compareTo(s) > 0) {
                result = s;
            }

            // --- Operation 1: Add 'a' to digits at odd indices ---
            char[] chars = s.toCharArray();
            for (int i = 1; i < chars.length; i += 2) {
                chars[i] = (char) ((chars[i] - '0' + a) % 10 + '0');
            }
            helper(String.valueOf(chars), a, b, seen);

            // --- Operation 2: Rotate string by 'b' positions ---
            helper(s.substring(b) + s.substring(0, b), a, b, seen);
        }
    }
}


/*
------------------------------------------------------------
Operations Allowed:
------------------------------------------------------------
1. Add 'a' to all digits at odd indices (mod 10).
2. Rotate the string to the right by 'b' positions.

Goal: Find the lexicographically smallest string 
      obtainable through any sequence of these operations.
------------------------------------------------------------


Example walkthrough ("5525", a = 9, b = 2):

Level 0:
└── "5525"
      ├── Add 9 to odd indices → "5424"
      └── Rotate by 2 → "2555"

Level 1:
├── "5424"
│     ├── Add 9 → "5323"
│     └── Rotate by 2 → "2454"
│
└── "2555"
      ├── Add 9 → "2454"  (seen)
      └── Rotate by 2 → "5525" (seen)
... and so on.


------------------------------------------------------------
Time Complexity Analysis:
------------------------------------------------------------

Let:
  n = length of string
  S = total unique states reachable

Each operation creates up to two new strings:
  - Adding 'a' to odd indices
  - Rotating by 'b'

However, duplicates are avoided via 'seen' set.

→ For "add" operation:
   Each odd position digit can take 10 possible values (0–9),
   so it cycles every 10 additions.

→ For "rotate" operation:
   Rotations repeat after `r = n / gcd(n, b)` steps,
   since rotating by 'b' repeatedly returns to the start after that many.

Hence, total possible states:
   S ≤ 10 * (n / gcd(n, b))  = O(n)

Each DFS call processes a string of length n:
   O(n) per state

⇒ Overall time: O(n * S) = O(n² / gcd(n, b))

------------------------------------------------------------
Space Complexity:
------------------------------------------------------------
O(S) for the 'seen' set = O(n / gcd(n, b))

*/
