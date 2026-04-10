class Solution {
    public int minimumDistance(int[] nums) {
        HashMap<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            hmap.putIfAbsent(nums[i], new ArrayList<>());
            hmap.get(nums[i]).add(i);
        }
        int dist = Integer.MAX_VALUE;

        for(int key : hmap.keySet()) {
            if(hmap.get(key).size() < 3)
                continue;
            List<Integer> temp  = hmap.get(key);
            for(int i=0; i<temp.size()-2; i++) {
                int x = temp.get(i+1)-temp.get(i);
                int y = temp.get(i+2)-temp.get(i);
                int z = temp.get(i+2)- temp.get(i+1);
                dist = Math.min(dist, x+y+z);
            }
        }
        return dist==Integer.MAX_VALUE ? -1 : dist;
        
    }
}