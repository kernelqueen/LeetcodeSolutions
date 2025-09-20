class Router {

    class Packet {
        public int source, dest, timestamp;
        Packet(int source, int dest, int timestamp) {
            this.source = source;
            this.dest = dest;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            Packet packet = (Packet) obj;
            return source == packet.source &&
                    dest == packet.dest &&
                    timestamp == packet.timestamp;
        }

        // [1,2,12] - [1,2,12]
        @Override public int hashCode() {
            return Objects.hash(source, dest, timestamp);
        }
    }

    HashSet<Packet> hset;                  
    Deque<Packet> dq;                               // FIFO queue
    HashMap<Integer, List<Integer>> destmap;        // dest → sorted timestamps
    int limit;
    public Router(int memoryLimit) {
        limit = memoryLimit;
        hset = new HashSet<>();
        dq = new ArrayDeque<>();
        destmap = new HashMap<>();
    }
    
    public boolean addPacket(int source, int dest, int timestamp) {
        // O(1)
        Packet packet = new Packet(source, dest, timestamp);

        // checking for duplicate packet
        if(hset.contains(packet)) {
            return false;
        }

        // evict the oldest packet if limit is reached
        if(dq.size() == limit) {
            Packet p = dq.remove();
            hset.remove(p);

            List<Integer> timestamps = destmap.get(p.dest); // O(n)

            // 2 -- > [10, 20, 30, 40]
            // 1 -- 110, 120, 130
            timestamps.remove(0);
            if (timestamps.isEmpty()) {
                destmap.remove(p.dest);
            }
        }

         // add new packet
        hset.add(packet);
        dq.offer(packet);

        destmap.computeIfAbsent(dest, k -> new ArrayList<>());
        destmap.get(dest).add(timestamp);

        return true;
    }

    // 1 -- 2 --  3 -- 4

    public int[] forwardPacket() {

        // O(1)
        if (dq.isEmpty()) {
            return new int[0];
        }

        Packet p = dq.remove();
        hset.remove(p);

        List<Integer> timestamps = destmap.get(p.dest); 
        
        timestamps.remove(0); // O(n)
        if (timestamps.isEmpty()) {
            destmap.remove(p.dest);
        }

        return new int[]{p.source, p.dest, p.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        // O(logn)
         if (!destmap.containsKey(destination)) {
            return 0;
        }
        // [1,2,3,4,5]
        // start = 2, end = 5
        // left = 1
        // right = 5
        // count = 5-1 = 4
        List<Integer> list = destmap.get(destination);
        int left = lowerBound(list, startTime);           // first ≥ startTime
        int right = upperBound(list, endTime);            // first > endTime
        return right - left;
    }


    private int lowerBound(List<Integer> list, int target) {
        // [1,2,3,4,5], target = 2
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int upperBound(List<Integer> list, int target) {
        // [1,2,3,4,5], target = 5
        // mid = 4, lo = 5
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);

 Router Memory Table (size: 3)

| **Queue Order (FIFO)** | **Source** | **Destination** | **Timestamp** |
| ---------------------- | ---------- | --------------- | ------------- |
| 3                      | 3          | 5               | 95            |
| 4                      | 4          | 5               | 105            |
| 5                      | 5          | 2               | 110            |


Data Structures:
1. FIFO Queue Deque [p1, p2, p3, p4]
2. HashSet - to check for duplicate packets
3. Dest --> [list of timestamps] [MAP]

add packet
    [1,4,90]
    [2,5,90]
    [1,4,90] X 
    [3,5,95] 
    [4,5,105]
<--forward packet-->
add packet
    [5,2,110]
get count [5,100,110] = 1


 */