/*O(n^2) Solution */
import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1); // default dry day value
        
        Map<Integer, Integer> lastRain = new HashMap<>();
        boolean[] zeroArr = new boolean[n]; // tracks dry days
        
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                zeroArr[i] = true; // mark this as a dry day
                continue;
            }
            
            int lake = rains[i];
            
            // If lake has rained before, we must dry it before this day
            if (lastRain.containsKey(lake)) {
                int prev = lastRain.get(lake);
                int dryDay = -1;

                // find any dry day between prev and current
                for (int j = prev + 1; j < i; j++) {
                    if (zeroArr[j]) {
                        dryDay = j;
                        break;
                    }
                }

                if (dryDay == -1) {
                    // no available dry day → impossible to avoid flood
                    return new int[0];
                }

                ans[dryDay] = lake;  // dry this lake on found dry day
                zeroArr[dryDay] = false; // mark dry day as used
            }

            ans[i] = -1; // raining day
            lastRain.put(lake, i);
        }

        return ans;
    }
}

/*O(nlogn) Solution */
class Solution {
    public int[] avoidFlood(int[] rains) {
        
        int n = rains.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n);

        HashMap<Integer, Integer> map = new HashMap<>();

        TreeSet<Integer> zero = new TreeSet<>();
        // [1,2,0,0,2,1]

        for(int i=0; i<n; i++) {
            if(rains[i]==0) {
                zero.add(i); // [2 <--> 3]
            }
            else {
                if(map.containsKey(rains[i])) {
                    int prevOccur = map.get(rains[i]);
                    // check if there is possibility to use a no rain day

                    Integer possible = zero.ceiling(prevOccur);
                    // The smallest element in the set that is greater than or equal to the given element e
                    if(possible==null)
                        return new int[0];
                    ans[possible] = rains[i];
                    zero.remove(possible);
                }
                ans[i] = -1;
                map.put(rains[i],i);
            }
            
        }

        return ans;

    }
}