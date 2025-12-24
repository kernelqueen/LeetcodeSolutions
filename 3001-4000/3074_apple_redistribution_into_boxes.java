class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        // m, n
        // O(mlogm) + O(n) + O(m)
        Arrays.sort(capacity);
        int sum = 0;
        for(int a : apple) {
            sum += a;
        }
        int ans = 0, i = capacity.length-1;

        while(sum > 0) {
            ans += 1;
            sum = sum - capacity[i--];
        }

        return ans;


    }
}