class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {

        // Maps to track frequency of original numbers and range updates
        Map<Integer, Integer> freq = new HashMap<>();  // original array numbers
        Map<Integer, Integer> diff = new HashMap<>();  // difference map for line sweep
        List<Integer> points = new ArrayList<>();      // all range boundaries + nums

        // Step 1: Build frequency and range difference maps
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            // Each number contributes +1 at (num - k) and -1 at (num + k + 1)
            diff.put(num - k, diff.getOrDefault(num - k, 0) + 1);
            diff.put(num + k + 1, diff.getOrDefault(num + k + 1, 0) - 1);

            // Collect key points for sweeping
            points.add(num);
            points.add(num - k);
            points.add(num + k + 1);
        }

        // Step 2: Sort and remove duplicates (coordinate compression)
        Collections.sort(points);
        int sz = 0;
        for (int i = 0; i < points.size(); i++) {
            if (i == 0 || !points.get(i).equals(points.get(i - 1))) {
                points.set(sz++, points.get(i));
            }
        }

        // Step 3: Line sweep to find max achievable frequency
        int ans = 0, common = 0;
        for (int i = 0; i < sz; i++) {
            int p = points.get(i);
            common += diff.getOrDefault(p, 0); // active count at this point

            int cur = freq.getOrDefault(p, 0); // current frequency
            int nearby = common - cur;         // elements that can be moved here
            int possible = cur + Math.min(nearby, numOperations);

            ans = Math.max(ans, possible);
        }

        return ans;
    }
}
/*
 * 
 * nums = [2, 4, 5]
k = 2
numOperations = 1
2 → [0 --- 4] → [0 -- 5)
4 → [2 --- 6] → [2 -- 7)
5 → [3 --- 7] → [3 -- 8)

Number 2:  [0 ======== 4]
             ↑---------↑
Number 4:      [2 ======== 6]
                 ↑---------↑
Number 5:        [3 ======== 7]
                   ↑---------↑

                   0 → +1
2 → +1
3 → +1
5 → -1
7 → -1
8 → -1

[0, 2, 3, 4, 5, 6, 7, 8]


| i | count | freq[i] | nearby | possible (= freq + min(nearby, ops)) |
| - | ----- | ------- | ------ | ------------------------------------ |
| 0 | 1     | 0       | 1      | 1                                    |
| 2 | 2     | 1       | 1      | 2                                    |
| 3 | 3     | 0       | 3      | 1                                    |
| 4 | 3     | 1       | 2      | 2                                    |
| 5 | 2     | 1       | 1      | 2                                    |
| 7 | 1     | 0       | 1      | 1                                    |
| 8 | 0     | 0       | 0      | 0                                    |

 */