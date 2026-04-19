class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int ans = 0;

        int i=0, j=0;
        // O(m+n)
        while(i<m && j<n) {
            if(nums1[i]<=nums2[j]) {
                ans = Math.max(ans, j-i);
                j++;
            }
            else i++;
        }
        
        return ans;
    }
}