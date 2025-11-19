class Solution {
    public int findFinalValue(int[] nums, int original) {
        HashSet<Integer> hset = new HashSet<>();

        for(int num : nums) {
            hset.add(num);
        }

        while(hset.contains(original)) {
            original = original*2;
        }

        return original;
    }
}