class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        // O(1)
        int minlen = String.valueOf(low).length();
        int maxlen = String.valueOf(high).length();

        List<Integer> ans = new ArrayList<>();
        for(int i=minlen; i<=maxlen; i++) {
            for(int start=0; start+i<=9; start++) {
                String substr = s.substring(start, start+i);
                int num = Integer.parseInt(substr);
                if(num>=low && num<=high)
                    ans.add(num);
            }
        }

        return ans;


    }
}