class Solution {
    public int mostBooked(int n, int[][] meetings) {

        // count[i] = number of meetings handled by room i
        int count[] = new int[n];

        // Min-heap of available rooms (smallest index first)
        // Ensures we always pick the lowest-numbered free room
        PriorityQueue<Integer> avail = new PriorityQueue<>();

        // Min-heap of busy rooms
        // Each entry = {endTime, roomIndex}
        // Ordered by earliest end time, tie-break by room index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] == b[0]) {
                    return Long.compare(a[1], b[1]);
                }
                return Long.compare(a[0], b[0]);
            }
        );

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Initially, all rooms are available
        for (int i = 0; i < n; i++) {
            avail.offer(i);
        }

        // Process each meeting in chronological order
        for (int[] m : meetings) {
            long stime = m[0];
            long etime = m[1];
            long duration = etime - stime;

            // Free up rooms whose meetings have ended before current start time
            while (!busy.isEmpty() && busy.peek()[0] <= stime) {
                avail.offer((int) busy.poll()[1]);
            }

            long endtime;
            int room;

            if (!avail.isEmpty()) {
                // If a room is available, assign the meeting immediately
                room = avail.poll();
                endtime = stime + duration;
            } else {
                // No room is free → delay meeting until earliest room becomes free
                long[] top = busy.poll();
                room = (int) top[1];
                endtime = top[0] + duration;
            }

            // Mark room as busy until endtime
            busy.offer(new long[]{endtime, room});

            // Increment meeting count for this room
            count[room]++;
        }

        // Find the room with the maximum number of meetings
        // If tie, smaller index wins (due to left-to-right scan)
        int max = 0, ans = -1;
        for (int i = 0; i < n; i++) {
            if (count[i] > max) {
                max = count[i];
                ans = i;
            }
        }

        return ans;
    }
}
