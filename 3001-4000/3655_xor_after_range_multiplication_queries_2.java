class Solution {

    static final int MOD = 1_000_000_007;

    int power(long base, long exp) {
        long ans = 1;
        while(exp > 0) {
            if((exp & 1) == 1) ans = (ans * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) ans;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int k = (int) Math.sqrt(n);

        List<List<int[]>> blocks = new ArrayList<>(); 
        for(int i = 0; i < k; i++) blocks.add(new ArrayList<>());

        for(int[] q : queries) {
            int l = q[0], r = q[1], step = q[2], val = q[3];  
            if(step < k) {
                blocks.get(step).add(new int[]{l, r, val});
            } else {
                while(l <= r) {
                    nums[l] = (int)(((long) nums[l] * val) % MOD);  
                    l += step;  
                }
            }
        }

        long[] mul = new long[n];

        for(int step = 1; step < k; step++) {
            if(blocks.get(step).isEmpty()) continue;
            Arrays.fill(mul, 1);

            for(int[] q : blocks.get(step)) {
                int l = q[0], r = q[1], val = q[2];
                mul[l] = (mul[l] * val) % MOD;
                int end = l + ((r - l) / step + 1) * step;
                if(end < n)
                    mul[end] = (mul[end] * power(val, MOD - 2)) % MOD;
            }

            for(int i = step; i < n; i++)
                mul[i] = (mul[i] * mul[i - step]) % MOD;

            for(int i = 0; i < n; i++)
                nums[i] = (int)(((long) nums[i] * mul[i]) % MOD);  
        }

        int ans = 0;
        for(int num : nums) ans ^= num;
        return ans;
    }
}