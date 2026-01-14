import java.util.*;

class Solution {

    // Each square creates two Y-events:
    // enter at y, exit at y + side, carrying its x-interval
    class Event {
        int y;
        int type;     // +1 = enter, -1 = exit
        int x;
        int side;

        Event(int y, int type, int x, int side) {
            this.y = y;
            this.type = type;
            this.x = x;
            this.side = side;
        }
    }

    // Segment tree over compressed x-intervals
    // Maintains UNION length of active x-intervals
    class SegmentTree {
        int n;              // number of x-intervals
        int[] coverCount;   // how many intervals fully cover this node
        double[] coveredLen; // union length contributed by this node
        int[] xs;           // compressed x-coordinates

        SegmentTree(int[] xs) {
            this.xs = xs;
            this.n = xs.length - 1;      // intervals = points - 1
            this.coverCount = new int[4 * n];
            this.coveredLen = new double[4 * n];
        }

        // Update coverage on [ql, qr) with +1 (add) or -1 (remove)
        void update(int node, int left, int right, int ql, int qr, int delta) {
            // no overlap
            if (qr <= left || right <= ql) return;

            // fully covered by update range
            if (ql <= left && right <= qr) {
                coverCount[node] += delta;
            } else {
                int mid = (left + right) / 2;
                update(node * 2, left, mid, ql, qr, delta);
                update(node * 2 + 1, mid, right, ql, qr, delta);
            }

            // recompute covered length
            if (coverCount[node] > 0) {
                // at least one interval covers entire segment
                coveredLen[node] = xs[right] - xs[left];
            } else if (left + 1 == right) {
                // leaf segment with no coverage
                coveredLen[node] = 0;
            } else {
                // sum from children
                coveredLen[node] =
                    coveredLen[node * 2] + coveredLen[node * 2 + 1];
            }
        }
    }

    public double separateSquares(int[][] squares) {
        // ---- Build events and collect x-coordinates ----
        List<Event> events = new ArrayList<>();
        Set<Integer> xSet = new HashSet<>();

        for (int[] s : squares) {
            // O(n)
            int x = s[0], y = s[1], side = s[2];
            events.add(new Event(y, 1, x, side));          // enter
            events.add(new Event(y + side, -1, x, side)); // exit
            xSet.add(x);
            xSet.add(x + side);
        }

        // ---- Coordinate compression on x ----
        int[] xs = xSet.stream().sorted().mapToInt(i -> i).toArray();
        Map<Integer, Integer> xIndex = new HashMap<>();
        for (int i = 0; i < xs.length; i++) xIndex.put(xs[i], i);

        // sort events by y
        events.sort(Comparator.comparingInt(e -> e.y));

        // ---- First sweep: compute total union area ----
        SegmentTree st = new SegmentTree(xs);
        double totalArea = 0;
        int prevY = events.get(0).y;

        for (Event e : events) {
            // O(n*logn) = O(nlogn)
            // O(n)
            int currY = e.y;
            // area added between prevY and currY
            totalArea += st.coveredLen[1] * (currY - prevY);

            // apply this event's x-interval
            st.update(
                1, 0, st.n,
                xIndex.get(e.x),
                xIndex.get(e.x + e.side),
                e.type
            );
            prevY = currY;
        }

        double half = totalArea / 2;

        // ---- Second sweep: find smallest y where area reaches half ----
        st = new SegmentTree(xs);
        double areaSoFar = 0;
        prevY = events.get(0).y;

        for (Event e : events) {
            int currY = e.y;
            double width = st.coveredLen[1];
            double dy = currY - prevY;

            // half is reached inside this horizontal strip
            if (areaSoFar + width * dy >= half) {
                // linear interpolation inside the strip
                return prevY + (half - areaSoFar) / width;
            }

            areaSoFar += width * dy;

            st.update(
                1, 0, st.n,
                xIndex.get(e.x),
                xIndex.get(e.x + e.side),
                e.type
            );
            prevY = currY;
        }

        return prevY;
    }
}
