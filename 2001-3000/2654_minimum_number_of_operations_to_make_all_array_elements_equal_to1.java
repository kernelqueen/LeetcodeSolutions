class Solution {
    public int minOperations(int[] nums) {
        // base case
        int n = nums.length;
        int onecount = 0;
        int gcdarr = 0;
        for(int num : nums) {
            if(num==1)
                onecount++; //[1,1,13,14,1]
            gcdarr = gcd(gcdarr, num);
        }

        if(onecount > 0) {
            return nums.length-onecount;
        }
        // [2,6,10,14] => gcd = x
        if(gcdarr > 1) {
            return -1; // impossible to convert all elements to 1
        }

        int smallestsize = nums.length;

        for(int i=0; i<nums.length; i++) {
            int gcd = 0;
            // O(n^2)*log(min(a,b))
            for(int j=i; j<nums.length; j++) {
                gcd = gcd(gcd, nums[j]);
                if(gcd==1) {
                    smallestsize = Math.min(smallestsize, j-i+1);
                    break;
                }
            }
        }

        return smallestsize-1+n-1; // ops to make one 1 

    }

    int gcd(int a, int b) {
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
}

/*
[2,6,3,4]

gcd(x,1) = 1

Two things:
 - We need to find one 1 in the array
 - adjacent two numbers whose gcd is 1 - co prime X

 nums = [6, 10, 15] 
 [6, 2, 15]
 [6, 1, 15]   
 [1, 1, 15]
 [1, 1, 1]

 What do we need to do?
 - to find the smallest subarray with gcd as 1 

 Input = [2,6,3,4]
         [2,6,1,4]
         [2,1,1,4]
         [1,1,1,4]
         [1,1,1,1]
 output = 4

*/