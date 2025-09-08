 /*------------------ Approach 1 (O(n)) ----------------------*/
class Solution {
    public int[] getNoZeroIntegers(int n) {
        
        for(int i=1; i<n; i++) {
            int a = i;
            int b = n-i;
            if(!String.valueOf(a).contains("0") && !String.valueOf(b).contains("0")) {
                return new int[]{a,b};
            }
        }

        return new int[0];

    }
}
 /*------------------ Approach 2 (logn) ----------------------*/


class Solution {
    public int[] getNoZeroIntegers(int n) {
        
        int a = 0, b = 0;

        int multiple = 1;
        // log10(n) = total number of digits
        // 10^4 -- 5 times -- O(1) 
        while(n>0) {
            int digit = n%10;
            n = n/10;

            if(digit==0) {
                n--;
                a += 1*multiple;
                b += 9*multiple;
            }
            else if(digit == 1 && n>0) {
                n--;
                a += 2*multiple;
                b += 9*multiple;
            }
            else {
                // default
                a += 1*multiple;
                b += (digit-1)*multiple;
            }

            multiple = multiple*10; // 1, 10, 100
        }

        return new int[]{a,b};
        

    }
}

/*

- Build two numbers digit by digit

- Split each digit to add up correctly

- Avoid zeros in either number

- Borrow for 0 or 1 → (1,9) or (2,9)

Example  n = 111

a + b = 111

n     Digit(d)   total          multiple     a   b
111    1          11[2,9]          1         2   9
10     0          10[1,9]          10        2 + (1)*10   9 + (10)*9  = 12,99
0     

n = 55
n     Digit(d)   n    multiple     a   b
55     5         5       1         1   4
5      5         0       10        11  44


*/

