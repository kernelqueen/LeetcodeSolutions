class Solution {
    public boolean judgeCircle(String moves) {
        int ydir=0, xdir=0;
        // O(n)
        for(int i=0; i<moves.length(); i++) {
            char ch = moves.charAt(i);
            if(ch=='U')
                ydir++;
            else if(ch=='D')
                ydir--;
            else if(ch=='L')
                xdir--;
            else xdir++;
        }
        return xdir==0 && ydir==0;

    }
}