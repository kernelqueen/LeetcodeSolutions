class Solution {
    public int nextBeautifulNumber(int n) {
        // 10^6 --- 7 digits, 666666, 999887
        // 1224444
        for(int i=n+1; i<=666666; i++) {
            if(isBeautiful(i)) {
                return i;
            }
        }

        return 1224444;
    }

    boolean isBeautiful(int inp) {
        String num = Integer.toString(inp);
        if(num.indexOf('0')!=-1)
            return false;

        int arr[] = new int[10];
        // 12223 , [0,1,3,1....]
        for(char ch : num.toCharArray()) {
            arr[ch-'0']++;
        }
        for(int i=1; i<arr.length; i++) {
            if(arr[i]>0 && arr[i]!=i)
                return false;
        }

        return true;
    }
}


/*Permutations Apporach */

class Solution {
    HashMap<Integer, List<String>> hmap = new HashMap<>();

    public Solution() {
        hmap.put(1, List.of("1"));
        hmap.put(2, List.of("22"));
        hmap.put(3, List.of("122", "333"));
        hmap.put(4, List.of("1333", "4444"));
        hmap.put(5, List.of("14444", "22333", "55555"));
        hmap.put(6, List.of("122333", "155555", "224444", "666666"));
        hmap.put(7, List.of("1224444", "2255555", "3334444", "7777777"));
    }

    public int nextBeautifulNumber(int n) {
        // current number digits and curr digits+1 checks

        String num = Integer.toString(n);
        int digits = num.length();

        int ans = 1224444;
        if(digits==7)
            return ans;

        for(String str : hmap.get(digits)) {

            List<String> perms = getPermutations(str);
            for(String perm : perms) {
                if(Integer.valueOf(perm) > n) {
                    ans = Math.min(ans, Integer.valueOf(perm));
                }
            }

        }

        for(String str : hmap.get(digits+1)) {

            List<String> perms = getPermutations(str);
            for(String perm : perms) {
                if(Integer.valueOf(perm) > n) {
                    ans = Math.min(ans, Integer.valueOf(perm));
                }
            }

        }
        return ans;
    }

    public List<String> getPermutations(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // to handle duplicates
        boolean[] used = new boolean[chars.length];
        backtrack(chars, new StringBuilder(), used, result);
        return result;
    }

    private void backtrack(char[] chars, StringBuilder path, boolean[] used, List<String> result) {
        if (path.length() == chars.length) {
            result.add(path.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            // skip duplicates
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            path.append(chars[i]);
            backtrack(chars, path, used, result);
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}


/*

1 --> 1
2 --> 22
3 --> 122, 333
4 --> 1333, 4444
5 --> 14444, 22333, 55555
6 --> 122333, 224444, 666666
7 --> 1224444, 2255555, 3334444, 7777777

*/

