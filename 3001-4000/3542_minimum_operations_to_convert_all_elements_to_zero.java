class Solution {
    public int minOperations(int[] nums) {
        Stack<Integer> st = new Stack<>();

        int count = 0;

        for(int num : nums) {
            while(!st.isEmpty() && st.peek() > num) {
                // removing larger elements in the middle
                st.pop();
            }

            if(st.isEmpty() || st.peek() < num) {
                // [0,1]
                if(num!=0)
                    count++; // current larger element would require operations
                st.push(num);
            }
        }
 
        return count;
    }
}
/*
[3,1,2,1]
[3] [1,2,1]
[0] [0,2,0]  --> 2 operations
[0] [0] [2] [0]
[0] [0] [0] [0] --> 1 operation


Brute Force Solution: 
- Take smallest element's left and right indices --> make it zero
- Need to traverse the array n time --> O(n^2) time complexity

Solution Approach
[1,2,1,2,1,2]

Traversing the array:

[1,]    => ops = 1
[1,2,]  => ops = 2 (as the larger element will need its own operation)
[1,2,1] => ops = 2 (is there a possibility to merge this one with some previous equal element?
                     YES -- previous 1 -- remove all larger mid elements)
           [1,1]
[1,1,2] => ops = 3
[1,1,2,1] => [1,1,1] (removing all larger mid elements)
[1,1,1,2] => ops = 4


Another ex - [3,1,2,1]

[3,] => ops = 1
[1,] => ops = 2  (no previous element equal to 1)
[1,2] => ops = 3  (larger element will need its own operation)
[1,2,1]  => [1,1] (can remove all larger middle elements and make use of previous 1)
All elements done, total ops = 3

*/