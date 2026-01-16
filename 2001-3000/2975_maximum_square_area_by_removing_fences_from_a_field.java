class Solution {
    static final int MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hfences, int[] vfences) {

        // add borders
        int[] h = new int[hfences.length + 2];
        int[] v = new int[vfences.length + 2];

        h[0] = 1;
        h[h.length - 1] = m;
        for (int i = 0; i < hfences.length; i++) {
            h[i + 1] = hfences[i];
        }

        v[0] = 1;
        v[v.length - 1] = n;
        for (int i = 0; i < vfences.length; i++) {
            v[i + 1] = vfences[i];
        }

        Arrays.sort(h);
        Arrays.sort(v);

        // store all horizontal distances
        HashSet<Integer> hDist = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hDist.add(h[j] - h[i]);
            }
        }

        long maxSide = -1;

        // check vertical distances against horizontal set
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int dist = v[j] - v[i];
                if (hDist.contains(dist)) {
                    maxSide = Math.max(maxSide, dist);
                }
            }
        }

        if (maxSide == -1) return -1;

        return (int) ((maxSide * maxSide) % MOD);
    }
}
