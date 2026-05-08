class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if(n==1)
            return 0;
        
        HashMap<Integer, List<Integer>> hmap = new HashMap<>();
        int max = -1; // 
        // O(n)
        for(int i=0; i<n; i++) {
            hmap.computeIfAbsent(nums[i], k->new ArrayList<>()).add(i);
            max = Math.max(nums[i], max);
        }

        int dist[] = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0; // 0th index

        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        // T.C - O(n*sqrt(max))
        // S.C. - O(n)
        while(!q.isEmpty()) {
            int curr = q.remove();

            List<Integer> nextjumps = new ArrayList<>();
            // adjacent elements
            if(curr-1>=0)
                nextjumps.add(curr-1);
            if(curr+1<n)
                nextjumps.add(curr+1);
            
            // prime teleportation
            // O(sqrt(num))
            if(isPrime(nums[curr])) {
                int p = nums[curr];
                for(int mul=p; mul<=max; mul+=p) {
                    if(hmap.containsKey(mul)) {
                        nextjumps.addAll(hmap.get(mul));
                        hmap.remove(mul);
                    }
                }
            }

            for(int next : nextjumps) {
                if(dist[next]==-1) {
                    dist[next] = dist[curr] + 1;
                    if(next==n-1)
                        return dist[next];
                        q.add(next);
                }
            }
            
        }
        return dist[n-1];
    }

    boolean isPrime(int num) {
        if(num<2) 
            return false;
        if(num==2)
            return true;
        if(num%2==0)
            return false;
        for(int i=3; i*i <=num; i+=2) {
            if(num%i==0)
                return false;
        }
        return true;
    }
}