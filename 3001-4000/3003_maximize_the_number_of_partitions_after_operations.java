/*Recursive Approach: Gives TLE */
class Solution {
    String s;
    int k;
    public int maxPartitionsAfterOperations(String s, int k) {
        this.k = k;
        this.s = s;
        return helper(0, 0, true); // max partitions
    }

    private int helper(int index, int bitmask, boolean canchange) {

        if(index==s.length())
            return 1; // current partition
        
        // case when not considering change

        int pos = s.charAt(index)-'a'; 
        int newbitmask = bitmask | (1<<pos);

        int ans = 0;

        if(Integer.bitCount(newbitmask) > k) {
            // found an answer
            ans =  1+helper(index+1, (1<<pos), canchange);
        }
        else {
            ans = helper(index+1, newbitmask, canchange);
        }

        // case when considering change
        if(canchange) {
            for(int i=0; i<26; i++) {
                int newmask = bitmask | (1<<i);
                int count = Integer.bitCount(newmask);

                if(count > k) {
                    ans = Math.max(ans, 1+helper(index+1, 1<<i , false));
                }
                else {
                    ans = Math.max(ans, helper(index+1, newmask, false));
                }
            }
        }

        return ans;


    }
}
/*Dp Approach using String as Key in hashmap */
class Solution {
    String s;
    int k;
    Map<String, Integer> memo = new HashMap<>();
    public int maxPartitionsAfterOperations(String s, int k) {
        this.k = k;
        this.s = s;
        return helper(0, 0, 1);
    }

    

private int helper(int index, int bitmask, int changeallowed) {
    if (index == s.length()) return 1;

    String key = index + "," + bitmask + "," + changeallowed;
    if (memo.containsKey(key)) return memo.get(key);

    int pos = s.charAt(index) - 'a';
    int newbitmask = bitmask | (1 << pos);
    int ans;

    if (Integer.bitCount(newbitmask) > k)
        ans = 1 + helper(index + 1, (1 << pos), changeallowed);
    else
        ans = helper(index + 1, newbitmask, changeallowed);

    if (changeallowed == 1) {
        for (int i = 0; i < 26; i++) {
            int newmask = bitmask | (1 << i);
            if (Integer.bitCount(newmask) > k)
                ans = Math.max(ans, 1 + helper(index + 1, (1 << i), 0));
            else
                ans = Math.max(ans, helper(index + 1, newmask, 0));
        }
    }

    memo.put(key, ans);
    return ans;
}

}

/*Bitwise DP Approach */
class Solution {
    String s;
    int k;
    Map<Long, Integer> memo = new HashMap<>();
    public int maxPartitionsAfterOperations(String s, int k) {
        this.k = k;
        this.s = s;
        return helper(0, 0, 1);
    }

private int helper(int index, int bitmask, int changeallowed) {
    if (index == s.length()) return 1;

    Long key = (long)index<<27 | (long)bitmask<<1 |changeallowed;
    if (memo.containsKey(key)) return memo.get(key);

    int pos = s.charAt(index) - 'a';
    int newbitmask = bitmask | (1 << pos);
    int ans;

    if (Integer.bitCount(newbitmask) > k)
        ans = 1 + helper(index + 1, (1 << pos), changeallowed);
    else
        ans = helper(index + 1, newbitmask, changeallowed);

    if (changeallowed == 1) {
        for (int i = 0; i < 26; i++) {
            int newmask = bitmask | (1 << i);
            if (Integer.bitCount(newmask) > k)
                ans = Math.max(ans, 1 + helper(index + 1, (1 << i), 0));
            else
                ans = Math.max(ans, helper(index + 1, newmask, 0));
        }
    }

    memo.put(key, ans);
    return ans;
}

}