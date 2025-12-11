class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {

        HashMap<Integer, int[]> rows = new HashMap<>();
        HashMap<Integer, int[]> cols = new HashMap<>();

        for(int build[] : buildings) {
            int x = build[0];
            int y = build[1];
            if(!rows.containsKey(y)) {
                rows.put(y, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            }
            if(!cols.containsKey(x)) {
                cols.put(x, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            }

            int xdir[] = rows.get(y);
            xdir[0] = Math.min(xdir[0], x); // extreme left, minimum
            xdir[1] = Math.max(xdir[1], x); // extreme right, maximum

            int ydir[] = cols.get(x);
            ydir[0] = Math.min(ydir[0], y);
            ydir[1] = Math.max(ydir[1], y);           
        }

        int count = 0; // count of buildings with four side cover

        for(int build[] : buildings) {
            int x = build[0];
            int y = build[1];

            int[] row = rows.get(y);
            int[] col = cols.get(x);

            if(x > row[0] && x <row[1]  && y > col[0] && y<col[1]) {
                count++;
            }
        }

        return count;
    }
}