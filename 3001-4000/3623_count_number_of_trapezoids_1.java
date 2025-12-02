class Solution {
    public int countTrapezoids(int[][] points) {
        
        HashMap<Integer, Integer> hmap = new HashMap<>();

        int mod = 1000000007;

        long ans = 0;
        long totaledges = 0;

        for(int point[] : points) {
            hmap.put(point[1], hmap.getOrDefault(point[1],0)+1);
        } // [0 --> 3], [2 --> 2] 

        for(int num : hmap.values()) { // O(n)
            long edges = ((long)num*(num-1))/2;

            ans = (ans + totaledges * edges)%mod; // total trapezoids

            totaledges = (totaledges + edges)%mod;
        }

        return (int)ans;

    }
}