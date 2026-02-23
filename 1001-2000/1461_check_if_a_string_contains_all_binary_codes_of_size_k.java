class Solution {
    public boolean hasAllCodes(String s, int k) {
        HashSet<String> hset = new HashSet<>();

        for(int i=0; i<s.length()-k+1; i++) {
            hset.add(s.substring(i,i+k));
        }
        return (hset.size()==Math.pow(2,k));
    }
}