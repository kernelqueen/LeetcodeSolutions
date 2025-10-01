class Solution {
    public int numWaterBottles(int filled, int exchange) {
        
        int ans = 0;
        int empty = 0;
        
        while( filled > 0 ) {
            
            ans += filled;
            empty += filled;
            filled = empty/exchange;
            empty = empty%exchange;

        }

        return ans;

    }
}

/*O(1) Solution Approach */
class Solution {
    public int numWaterBottles(int n, int e) {
        
        return n + (n-1)/(e-1);

    }
}