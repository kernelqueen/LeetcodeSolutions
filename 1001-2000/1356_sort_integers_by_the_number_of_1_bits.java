class Solution {
    public int[] sortByBits(int[] arr) {
        // O(nlogn)
        // O(n)
        Integer temp[] = new Integer[arr.length];
        for(int i=0; i<arr.length; i++)
            temp[i] = arr[i];
        Arrays.sort(temp, (a,b)-> {
            int diff = Integer.bitCount(a)-Integer.bitCount(b);
            return diff!=0 ? diff : a-b;
            //  1024, 512 --> 512 
        });
        
        int ans[] = new int[arr.length];
        for(int i=0; i<arr.length; i++)
            ans[i] = temp[i];
        
        return ans;


    }
}