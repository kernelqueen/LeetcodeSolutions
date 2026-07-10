// Approach: Build an adjacency list, then DFS each connected component to count its nodes and
// edges; a component of k nodes is complete when it has k*(k-1)/2 edges (2*that with both directions).
// Time: O(V + E) | Space: O(V + E)

class Solution {
    HashMap<Integer, List<Integer>> hmap;
    boolean visited[];
    public int countCompleteComponents(int n, int[][] edges) {
        hmap = new HashMap<>();
        visited = new boolean[n];

        // Build undirected adjacency list
        for(int edge[] : edges) {
            int src = edge[0], dest = edge[1];
            if(!hmap.containsKey(src))
                hmap.put(src, new ArrayList<>());
            if(!hmap.containsKey(dest))
                hmap.put(dest, new ArrayList<>());
            hmap.get(src).add(dest);
            hmap.get(dest).add(src);
        }

        int ans = 0;
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                int temp[] = dfs(i); // [node count, edge-endpoint count]
                int ncount = temp[0], e = temp[1];
                // e counts each edge twice, so a complete component satisfies e == k*(k-1)
                if((ncount*(ncount-1) == e) || ncount==1)
                    ans++;
            }
        }

        return ans;


    }

    // Returns {number of nodes, sum of degrees} for the component containing `node`
    int[] dfs(int node) {
        int ret[] = new int[2];

        visited[node] = true;
        ret[0] += 1;
        List<Integer> list = hmap.get(node);

        if(list==null)
            return ret; // isolated node with no edges

        ret[1] += list.size(); // add this node's degree to the endpoint count

        for(int i=0; i<list.size(); i++) {
            if(visited[list.get(i)])
                continue;
            int t[] = dfs(list.get(i));
            ret[0] += t[0];
            ret[1] += t[1];
        }

        return ret;
    }
}