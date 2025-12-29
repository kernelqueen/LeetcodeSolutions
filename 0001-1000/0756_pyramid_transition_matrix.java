class Solution {
    Map<String, List<Character>> hmap;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        hmap = new HashMap<>();
        for(int i=0; i<allowed.size(); i++) {
            String str = allowed.get(i);
            String key = str.substring(0,2);
            if(!hmap.containsKey(key)) {
                hmap.put(key, new ArrayList<>());
            }
            hmap.get(key).add(str.charAt(2));
        }
        return helper(bottom, "", 0);
    }
    boolean helper(String row, String next, int index) {
        if(row.length()==1)
            return true; // went to the top of the pyramid
        // if nextrow is just one less than current row, start again with this row
        if(next.length()==row.length()-1) {
            return helper(next, "", 0);
        }    
        String key = row.substring(index, index+2);
        if(!hmap.containsKey(key))            return false; // we can't get to next row for this block
        for(char ch : hmap.get(key)) {
            if(helper(row, next+ch, index+1))
                return true;
        }
        return false;
    }
}