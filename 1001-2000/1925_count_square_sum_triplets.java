class Solution {
    public int countTriples(int n) {
        
        int count = 0;

        for(int a=1; a<=n; a++) {
            for(int b=a+1; b<=n; b++) {
                //O(n^2)
                double sqrt = Math.sqrt(a*a + b*b);
                if(sqrt == Math.floor(sqrt) && sqrt<=n) {
                    count+=2; // [3,4] ,[4,3]
                }
            }
        }
        return count;
    }
}