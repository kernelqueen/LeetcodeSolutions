class Solution {
    long dp[]; //O(N)
    long MAX_COST = 1000L * 100L * 1000000L;
    Map<Integer, Set<String>> lengthToStrings; // O(M*K)
    Map<String, Map<String, Long>> mincost; // M*k
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        dp = new long[source.length()+1];
        Arrays.fill(dp, -1);

        lengthToStrings = new HashMap<>();
        mincost = new HashMap<>();

        // populate map
        // source.length = N
        // original.length = M*K
        // O(M*K)
        for(int i=0; i<original.length; i++) {
            int len = original[i].length();
            lengthToStrings.putIfAbsent(len, new HashSet<>());
            lengthToStrings.get(len).add(original[i]);

            mincost.putIfAbsent(original[i], new HashMap<>());
            // String --> Map[[Str1, cost]]
            Map<String, Long> temp = mincost.get(original[i]);

            if(temp.containsKey(changed[i])) {
                long currcost = temp.get(changed[i]);
                temp.put(changed[i], Math.min(currcost, (long)cost[i]));
            } else {
                temp.put(changed[i], (long)cost[i]);
            }
        }

        // Floyd Warshall Algo
        // U = number of unique strings
        // C = count of distinct string lengths

        // O(C*(U^3))
        for(Map.Entry<Integer, Set<String>> entry : lengthToStrings.entrySet()) {
            Set<String> strings = entry.getValue(); // 1---, 2--, 3--- 
            // start -- > middle --> end
            for(String middle : strings) {
                for(String start : strings) {
                        // mincost 
                    Map<String, Long> fromStart = mincost.get(start);
                    if(fromStart!=null && fromStart.containsKey(middle)) {
                        Map<String, Long> fromMiddle = mincost.get(middle);
                        if(fromMiddle!=null) {
                            for(Map.Entry<String, Long> endEntry : fromMiddle.entrySet()) {
                                String end = endEntry.getKey();
                                long costViaMiddle = endEntry.getValue();
                                if(!fromStart.containsKey(end)) {
                                    fromStart.put(end, MAX_COST + 1);
                                }
                                long min = Math.min(fromStart.get(end), fromStart.get(middle) + costViaMiddle);
                                fromStart.put(end, min);
                            }
                        }
                        
                    }
                    
                }
            }
        }

        long ans = helper(0, source, target);
        return ans > MAX_COST ? -1 : ans;
    }

    long helper(int i, String source, String target) {
        if(i>=source.length()) {
            return 0;
        }

        if(dp[i]==-1) {
            dp[i] = MAX_COST + 1;

            if(source.charAt(i)==target.charAt(i)) {
                dp[i] = helper(i+1, source, target);
            }
            // Dp array size = N
            // create substring = O(K), HashMap lookup = O(k)
            // C = count of unique length strings
            // O(N*K*K*C)
            for(Map.Entry<Integer, Set<String>> entry : lengthToStrings.entrySet()) {
                int len = entry.getKey();
                if(i+len <= source.length()) {
                    String source_substr = source.substring(i, i+len);
                    String target_substr = target.substring(i, i+len);
                    if(mincost.containsKey(source_substr)) {
                        Map<String, Long> reachable = mincost.get(source_substr);
                        if(reachable.containsKey(target_substr)) {
                            long currmin = reachable.get(target_substr) + helper(i+len, source, target);
                            dp[i] = Math.min(dp[i], currmin);
                        }
                    }

                }
            }
         } 

         return dp[i];
    }
}






