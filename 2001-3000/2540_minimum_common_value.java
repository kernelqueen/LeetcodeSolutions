class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i=0, j=0;
        // O(m+n)
        // O(1)
        while(i<nums1.length && j<nums2.length) {
            if(nums1[i]==nums2[j])
                return nums1[i];
            else if(nums1[i] < nums2[j])
                i++;
            else j++;
        }
        return -1;
    }
}

/*

[1,2,3,6]
[2,3,4,5,8,9]

*/