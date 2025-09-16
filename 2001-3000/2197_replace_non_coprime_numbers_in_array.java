class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        
        Stack<Integer> st = new Stack<>();
        // O(n)
        for(int num : nums) {
            st.push(num);

            while(st.size() > 1 ) {
                int first = st.pop();
                int second = st.pop();

                int gcd = getGCD(first, second);

                // if numbers are non co prime
                if(gcd > 1) {
                    int lcm = first * (second/gcd);
                    st.push(lcm);
                }
                else {
                    st.push(second);
                    st.push(first);
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!st.isEmpty()) {
            ans.add(st.pop());
        }

        Collections.reverse(ans);

        return ans;

    }
    // Eucidian algo for getting GCD
    // O(log(min(a,b)))
    private int getGCD(int a, int b) {
        if(b==0)
            return a;
        return getGCD(b, a%b);
    }
}


// T.C = O(nlogm)
/*
num1 = 6, num2= 4
lcm * gcd = num1 * num2

24 = 2*lcm
lcm = 12






Example Explaination:

nums = [6,4,3,2,7,6,2]




Prime Numbers: 2,3,5,7 

co-prime [4,7] , GCD = 1

non co prime [2,4] , common , GCD > 1


*/