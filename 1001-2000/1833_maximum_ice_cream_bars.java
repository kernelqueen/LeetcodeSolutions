class Solution {
    public int maxIceCream(int[] costs, int coins) {
        
        int count[] = new int[100001];

        for(int cost : costs) {
            count[cost]++;
        }

        int ans = 0;
        for(int i=1; i<count.length && coins>=i; i++) {
            while(count[i] > 0 && coins >= i) {
                ans++;
                count[i]--;
                coins -= i;
            }
        }
        return ans;
    }
}