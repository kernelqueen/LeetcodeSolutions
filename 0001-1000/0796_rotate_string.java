class Solution {
    public boolean rotateString(String s, String goal) {

        if(goal.length()!=s.length())
            return false;
        
        String temp = s + s;

        if(temp.indexOf(goal)!=-1)
            return true;
        return false;

    }
}