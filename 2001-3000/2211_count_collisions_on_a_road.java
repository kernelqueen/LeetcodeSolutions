class Solution {
    public int countCollisions(String dirs) {
        
        int i = 0, j = dirs.length()-1;

        while( i < j && dirs.charAt(i)=='L') {
            i++;
        }
        while( j >i && dirs.charAt(j)=='R') {
            j--;
        }
        if(i==j) {
            return 0; //LLLLRRR
        }

        int ans = 0;

        while(i<=j) {
            if(dirs.charAt(i)!='S') {
                ans++;
            }
            i++;
        }

        return ans;

    }
}