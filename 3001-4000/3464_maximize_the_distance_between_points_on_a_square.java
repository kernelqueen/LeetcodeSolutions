class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        
        List<Long> arr = new ArrayList<>();

        for(int p[] : points) {
            int x = p[0], y = p[1];
            if(x==0)         arr.add((long)y);
            else if(y==side) arr.add((long)side + x);
            else if(x==side) arr.add(3L*side - y);
            else             arr.add(4L*side - x); 

        }
        Collections.sort(arr); // nlogn

        // Binary Search
        long low = 1, high = 2*side;

        int ans = 0;
        while(low <=high) {
            // log(side)
            
            long mid = low + (high-low)/2;
            
            if(isValid(arr, side, k, mid)) {
                low = mid+1;
                ans = (int)mid;
            } else high = mid-1;
        }

        return ans;
    }

    boolean isValid(List<Long> arr, int side, int k, long dist) {
        long peri = side * 4l;

        for(int i=0; i<arr.size(); i++) {
            // O(log(side)*n*O(k.logn))
            // O(n)
            long start = arr.get(i);
            long end = start + peri - dist;

            for(int j=0; j<k-1; j++) {
                int next = lower(arr, start+dist);
                if(next>=arr.size() || arr.get(next)>end) {
                    start = -1;
                    break;
                }
                start = arr.get(next);                    
            }

            if(start >= 0)
                return true;
        }

        return false;
    }

    int lower(List<Long> arr, long target) {

        int low = 0, high = arr.size();
        while(low < high) {
            int mid = low + (high-low)/2;
            if(arr.get(mid) < target)  
                low = mid+1;
            else
            high = mid;
           
        }
        return low;

    }
}