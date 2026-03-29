class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // O(1) - O(1)
        if(s1.equals(s2))
            return true;
        // case 1: 1 -- 3 (same), 0 or 2
        if(s1.charAt(1)==s2.charAt(1) && s1.charAt(3)==s2.charAt(3)
        && s1.charAt(0)==s2.charAt(2) && s1.charAt(2)==s2.charAt(0))
            return true;
        // case2 : 0 -- 2 (same), 1 or 3 swap
        if(s1.charAt(0)==s2.charAt(0) && s1.charAt(2)==s2.charAt(2)
        && s1.charAt(1)==s2.charAt(3) && s1.charAt(3)==s2.charAt(1))
            return true;
        // swaping both indices
        if(s1.charAt(1)==s2.charAt(3) && s1.charAt(3)==s2.charAt(1)
        && s1.charAt(0)==s2.charAt(2) && s1.charAt(2)==s2.charAt(0))
            return true;
        return false;
        
    }
}

// 0 - 2 - 
// 1 - 3 - 