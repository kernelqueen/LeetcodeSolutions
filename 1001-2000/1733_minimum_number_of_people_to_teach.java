class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        
        // mapping users to their languages 

        Map<Integer, Set<Integer>> personLanguages = new HashMap<>();

        for(int i=0; i<languages.length; i++) {
            Set<Integer> langs = new HashSet<>();
            for(int lang : languages[i]) {
                langs.add(lang);
            }
            personLanguages.put(i+1, langs);
        }

        // people who can not communicate who are friends with each other
        Set<Integer> peopleToConsider = new HashSet<>();
        for (int[] pair : friendships) {
            int p1 = pair[0], p2 = pair[1];
            Set<Integer> langs1 = personLanguages.get(p1);
            Set<Integer> langs2 = personLanguages.get(p2);

            // If no common language, add both people to the set
            boolean canCommunicate = false;
            for (int lang : langs1) {
                if (langs2.contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }
            if (!canCommunicate) {
                peopleToConsider.add(p1);
                peopleToConsider.add(p2);
            }
        }

        // check for each language if we can choose it to minimise the number to teach people
        int minans = Integer.MAX_VALUE;

        for(int lang=1; lang<=n; lang++) {
            int count = 0;

            for(int person : peopleToConsider) {
                if(!personLanguages.get(person).contains(lang)) {
                    count++;
                }
            }

            minans = Math.min(minans, count);
        }

        return minans;

    }
}
