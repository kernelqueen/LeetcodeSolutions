/*--------HashSet Approach O(n) Space complexity -------- */
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        HashSet<Integer> hset = new HashSet<>();
        int ans[] = new int[2];
        int index = 0;
        for(int num : nums) {
            if(hset.contains(num)) {
                ans[index++] = num;
            }
            hset.add(num);
        }
        return ans;
    }
}

/*--------Bitwise Approach O(1) Space complexity -------- */

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length-2;
        int xor = 0;
        for(int num : nums) {
            xor = xor ^ num;
        }
        for(int i=0; i<n; i++) {
            xor = xor^i;
        }
        int bit = Integer.highestOneBit(xor); // for 7(111) --> we get 100 or 4
        int a = 0, b = 0;

        for(int i=0; i<n; i++) {
            if((i & bit)!=0) {
                a = a ^ i;
            }
            else b = b ^ i;
        }
        for(int num : nums) {
            if((num & bit)!=0) 
                a = a ^ num;
            else b = b ^num;
        }

        return new int[]{a,b};


    }
}