// ============================================================
// LeetCode 1306 - Jump Game III
// ============================================================

// -------------------------
// Approach 1: DFS (Recursive)
// Time: O(n) | Space: O(n)
// -------------------------
class Solution {
    boolean visited[];

    public boolean canReach(int[] arr, int start) {
        visited = new boolean[arr.length];
        return dfs(arr, start);
    }

    boolean dfs(int[] arr, int index) {
        if (index < 0 || index >= arr.length || visited[index])
            return false;
        if (arr[index] == 0)
            return true;
        visited[index] = true;
        return dfs(arr, index - arr[index]) || dfs(arr, index + arr[index]);
    }
}

// -------------------------
// Approach 2: BFS (Iterative)
// Time: O(n) | Space: O(n)
// -------------------------
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        if (arr[start] == 0)
            return true;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int curr = q.poll();
            visited.add(curr);

            int next = curr + arr[curr];
            int prev = curr - arr[curr];

            if (next < n && !visited.contains(next)) {
                if (arr[next] == 0) return true;
                q.offer(next);
            }
            if (prev >= 0 && !visited.contains(prev)) {
                if (arr[prev] == 0) return true;
                q.offer(prev);
            }
        }

        return false;
    }
}