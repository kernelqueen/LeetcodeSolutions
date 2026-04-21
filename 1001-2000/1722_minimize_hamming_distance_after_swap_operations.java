class Solution {
    int parent[];

    void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);
        if(p1==p2)
            return;
        if(p1 < p2) 
            parent[p2] = p1;
        else parent[p1] = p2;
    }

    int find(int x) {
        if(parent[x]!=x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] swaps) {
        // O(n + m)
        // O(n)
        int n = source.length;
        parent = new int[n];
        for(int i=0; i<n ;i++) {
            parent[i] = i;
        }
        for(int edge[] : swaps) {
            union(edge[0], edge[1]);
        }

        HashMap<Integer, HashMap<Integer, Integer>> hmap = new HashMap<>();
        for(int i=0; i<n; i++) {

            int parent = find(i);

            if(!hmap.containsKey(parent)) 
                hmap.put(parent, new HashMap<>());

            hmap.get(parent).put(source[i], hmap.get(parent).getOrDefault(source[i], 0) + 1);

        }

        int ans = 0;

        for(int i=0; i<n; i++) {
            int parent = find(i);
            HashMap<Integer, Integer> temp = hmap.get(parent);
            if(temp.containsKey(target[i])) {
                temp.put(target[i], temp.get(target[i])-1);
                if(temp.get(target[i])==0)
                    temp.remove(target[i]);
            } else ans++;
        }

        return ans;

    }
}