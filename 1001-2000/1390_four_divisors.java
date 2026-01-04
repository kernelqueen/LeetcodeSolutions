/*************Brute Force Solution *********** */
class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            int count = 0;
            int sum = 0;
            for(int div=1; div<=num; div++) {
                if(num%div==0) {
                    count++;
                    sum += div;
                }
                if(count > 4) {
                    break;
                }
            }
            if(count==4) {
                ans += sum;
            }
        }
        return ans;
    }
}

/**************Optimized Solution *********** */
class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for(int i=0; i<nums.length; i++) {
            // O(n)
            int num = nums[i];
            int count = 0;
            int sum = 0;
            // nums[i] = m
            // O(sqrt(m))
            for(int div=1; div*div<=num; div++) {
                if(num%div==0) {
                    count+=2; // n , n/d, 1, n 
                    sum += (div + num/div) ;
                }
                if(count > 4) {
                    break;
                }
            }
            if(count==4 && !perfectSquare(num)) {
                // num should not be prefect square
                // [1,2,4]

                ans += sum;
            }
        }
        return ans;
    }

    boolean perfectSquare(int n) {
        int sqrt = (int)Math.sqrt(n);
        if(sqrt*sqrt==n) {
            return true;
        }
        return false;
    }
}


/*
Four divisors: 1, _ , _ , _ n

d
n/d


These two middle divisors should be equal if the number is perfect square:

[Ex: n=4, div = 1,2,2,4]

Otherwise Two divisors: d and n/d

*/