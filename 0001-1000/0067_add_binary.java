class Solution {
    public String addBinary(String a, String b) {
        int m = a.length()-1;
        int n = b.length()-1;
        // O(max(m,n))
        // O(max(m,n))
        StringBuilder sb = new StringBuilder(); 
        int carry = 0;
        // "1000" "1"
        // 1001
        while(m>=0 || n>=0) {
            int sum = carry;
            if(m>=0) {
                sum += (a.charAt(m) - '0');
                m--;
            }
            if(n>=0) {
                sum += (b.charAt(n) - '0');
                n--;
            }
            sb.append(sum%2);
            carry = sum/2;
        }

        if(carry==1)
            sb.append('1');
        
        return sb.reverse().toString();

        
    }
}