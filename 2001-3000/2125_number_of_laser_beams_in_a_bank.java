class Solution {
    public int numberOfBeams(String[] bank) {
        int count = 0;

        int prev = 0;

        for(String floor : bank) {
            int curr = 0; // total security devices
            for(int i=0; i<floor.length(); i++) {
                if(floor.charAt(i)=='1') {
                    curr++;
                }
            }

            count += curr*prev;

            prev = curr>0 ? curr : prev;
        }

        return count;
    }
}