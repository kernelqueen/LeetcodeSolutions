// Approach: Greedy monotonic stack — keep result lexicographically smallest by popping a larger
// preceding char whenever it still appears later; skip chars already in the stack.
// Time: O(n) | Space: O(n)

class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();

        // last[c] = last index at which character c appears
        int last[] = new int[26];
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            last[ch-'a']= i;
        }

        Stack<Integer> st = new Stack<>();
        HashSet<Character> hset = new HashSet<>(); // tracks which chars are currently in the stack
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if(hset.contains(ch))
                continue; // already placed; keep its earlier (better) position
            // Pop a bigger char only if it reappears later, so we can re-add it then
            while(!st.isEmpty()) {
                char prev = s.charAt(st.peek());
                if(prev > ch && last[prev-'a']>i) {
                    st.pop();
                    hset.remove(prev);
                } else break;
            }

            st.push(i);
            hset.add(ch);
        }

        // Stack holds indices bottom-to-top; pop reverses order, so reverse back
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(s.charAt(st.pop()));
        }
        sb.reverse();
        return sb.toString();
    }
}