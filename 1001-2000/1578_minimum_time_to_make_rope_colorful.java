class Solution {
    public int minCost(String colors, int[] time) {
        

        int cost = 0;

        for(int i=0; i<colors.length(); ) {

            int max = 0, sum = 0;
            char ch = colors.charAt(i);

            while(i<colors.length() && ch==colors.charAt(i)) {
                sum += time[i];
                max = Math.max(time[i], max);
                i++;
            }

            cost += (sum-max);

        }
        return cost;

    }
}