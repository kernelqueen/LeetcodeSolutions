class Solution {
    public List<String> readBinaryWatch(int LEDs) {
        List<String> ans = new ArrayList<>();
        
        //  try all possible hour and minute combinations
        for(int h = 0; h < 12; h++) {
            for(int m = 0; m < 60; m++) {
                // Check if the total number of lit LEDs (1 bits) equals the target
                // Integer.bitCount() returns the number of 1 bits in binary representation
                if(Integer.bitCount(h) + Integer.bitCount(m) == LEDs) {
                    StringBuilder sb = new StringBuilder();
                    
                    // Build time string in "H:MM" format
                    sb.append(h);
                    sb.append(":");
                    
                    // Add leading zero for minutes less than 10 (e.g., "1:05" not "1:5")
                    if(m < 10) {
                        sb.append("0" + m);
                    }
                    else {
                        sb.append(m);
                    }
                    
                    ans.add(sb.toString());
                }
            }
        }
        
        return ans;
    }
}