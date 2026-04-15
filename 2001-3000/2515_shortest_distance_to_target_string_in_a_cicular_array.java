class Solution {
    public int closestTarget(String[] words, String target, int start) {
        int n = words.length;
        int ans = n+1;
        // O(n), O(1)
        for(int i=0; i<n; i++) {
            if(words[i].equals(target))
            {
                int dist1 = Math.abs(start-i);
                int dist2 = n - dist1;
                ans = Math.min(ans, Math.min(dist1, dist2));
            }
        }

        if(ans > n)
            return -1;
        return ans;

    }
}








/*
n = 7
start = 2, targetIndex = 5
0 1 2 3 4 5 6




dist1 = abs(2-5) = 3
dist2 = n-dist1

*/