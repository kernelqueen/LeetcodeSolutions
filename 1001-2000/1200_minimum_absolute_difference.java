class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr); // nlogn
        List<List<Integer>> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        // O(n)
        for(int i=1; i<arr.length; i++) {
            min = Math.min(min, arr[i]-arr[i-1]);
        }
        for(int i=1; i<arr.length; i++) {
            int diff = arr[i]-arr[i-1];
            if(diff==min) {
                ans.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }
        return ans;
    }
}