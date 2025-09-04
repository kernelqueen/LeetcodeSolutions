class Solution {
    public int findClosest(int x, int y, int z) {
        int xdist = Math.abs(z - x);

        int ydist = Math.abs(z - y);

        // If both distances are equal, both persons reach at the same time
        if (xdist == ydist)
            return 0;

        // Otherwise, return 1 if Person 1 is closer, else return 2
        return xdist > ydist ? 2 : 1;
    }
}
