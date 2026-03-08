class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            char flip = nums[i].charAt(i)=='0' ? '1' : '0';
            sb.append(flip);
        }
        return sb.toString();
        
    }
}

/*
n = 3
1 0 1
0 1 1
1 1 1

ans = 0 0 0
*/