class Solution {
    public int maxOperations(String s) {
        
        int zerostring = 0;

        int ans = 0; 

        for(int i=s.length()-1; i>=0; ) {

            if(s.charAt(i)=='0')
                zerostring++;
            while(i>=0 && s.charAt(i)=='0') {
                i--;
            }

            int countones = 0;

            while(i>=0 && s.charAt(i)=='1') {
                countones++;
                i--;
            }

            ans += countones*zerostring;

             
        }

        return ans;

    }
}


/*

    s = "1001101"
i=0 s = "0011101"
i=4 s = "0011011"
i=3 s = "0010111"
i=2 s = "0001111"

*/