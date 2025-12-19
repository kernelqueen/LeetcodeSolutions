class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int first) {

        // O(mlogm)
        // O(n+m)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int meeting[] : meetings) {
            int x = meeting[0], y= meeting[1], time = meeting[2];
            if(!graph.containsKey(x)) {
                graph.put(x, new ArrayList<>());
            }
            if(!graph.containsKey(y)) {
                graph.put(y, new ArrayList<>());
            }
            graph.get(x).add(new int[]{time, y});
            graph.get(y).add(new int[]{time, x});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0]-b[0]);

        pq.offer(new int[]{0,0});
        pq.offer(new int[]{0, first});

        HashSet<Integer> visited = new HashSet<>();
        while(!pq.isEmpty()) {
            int curr[] = pq.remove();
            int time = curr[0], person = curr[1];
            if(!visited.contains(person)) {
                visited.add(person);
                for(int[] p : graph.getOrDefault(person, new ArrayList<>())) {
                    if(!visited.contains(p[1]) && p[0]>= time) {
                        pq.offer(p);
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        ans.addAll(visited);
        return ans;

    }
}