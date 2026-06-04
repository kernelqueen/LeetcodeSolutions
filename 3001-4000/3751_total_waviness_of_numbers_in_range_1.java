class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;
        // O(n*d)
        for(int num = num1; num<=num2; num++) {
            ans += getWaviness(num);
        }
        return ans;
    }

    int getWaviness(int num) {
        String s = Integer.toString(num);
        int count = 0;
        char arr[] = s.toCharArray();
        for(int i=1; i<arr.length-1; i++) {
            if((arr[i] > arr[i-1] && arr[i] > arr[i+1]) || 
            (arr[i] < arr[i-1] && arr[i] < arr[i+1]))
                count++;
        }
        return count;
    }
}