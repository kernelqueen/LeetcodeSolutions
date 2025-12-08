class Solution {
    public int countOdds(int low, int high) {
        
        // count of odd numbers b/w 1 to low-1 = low/2
        // count ........ b/w 1 to high = (high+1)/2

        return (high+1)/2 - low/2;

    }
}
// n --> [1,n] --> (n+1)/2
// n = 3 [1,3] 4/2
// n = 4 [1,3] 5/2
// [1.. high] - [1..low]
// [1..low] --> low/2

/*
Four cases
Low: Odd , High: Odd
Low: Even, High: Even
Low: Even, High: Odd
Low: Odd, High: Even

*/