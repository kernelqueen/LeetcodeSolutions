class Solution {

    HashMap<Integer, List<Integer>> hmap;
    HashSet<Integer> visited;
    int count;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        hmap = new HashMap<>(); // O(n)
        visited = new HashSet<>();
        for(int i=0; i<n; i++) {
            hmap.put(i, new ArrayList<>());
        }

        // adding edges
        for(int edge[] : edges) {
            // O(n)
            int src = edge[0];
            int dest = edge[1];
            hmap.get(src).add(dest);
            hmap.get(dest).add(src);
        }

        count = 0; // count of components 

        helper(0, values, k); // DFS function
        return count;

    }

    int helper(int src, int values[], int k) {
        // O(n)
        if(visited.contains(src)) {
            return 0;
        }
        visited.add(src);
        int sum = values[src];
        List<Integer> list = hmap.get(src);
        for(int curr : list) {
            sum += helper(curr, values, k);
        }

        if(sum%k==0)
            count++;
        return sum%k;
    }
}