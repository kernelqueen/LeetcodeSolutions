class Solution {
    public int[] countMentions(int n, List<List<String>> events) {
        
        int mentions[] = new int[n];

        // sorting events on the basis of timestamps
        // O(mlogm)
        Collections.sort(events, (a,b) -> {
            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));

            if(t1!=t2) {
                return Integer.compare(t1, t2);
            }

            // t1==t2
            if(a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) {
                return 1;
            } else return -1;
        });

        // offline users
        int value = 0;
        HashMap<Integer, Integer> offline = new HashMap<>();
        // O(n*m)
        for(int i=0; i<events.size(); i++) {
            String type = events.get(i).get(0); // "MESSAGE" or "OFFLINE"
            int time = Integer.parseInt(events.get(i).get(1));

            if(type.equals("MESSAGE")) {
                // ALL, HERE, listofids
                String m_string = events.get(i).get(2);
                if(m_string.equals("ALL")) {
                    value++;
                } else if(m_string.equals("HERE")) {
                    increaseOnline(mentions, offline, time);
                } else {
                    String sids = events.get(i).get(2);
                    String ids[] = sids.split(" "); // id0, id1, id2
                    // O(n)
                    for(String id : ids) {
                        int intid = Integer.parseInt(id.substring(2));
                        mentions[intid]++;
                    }
                }
            }
            else {
                // offline 
                int id = Integer.parseInt(events.get(i).get(2));
                offline.put(id, time);
            }
        }
        increaseAll(mentions, value);

        return mentions;

    }

    void increaseAll(int arr[], int value) {
        for(int i=0; i<arr.length; i++) {
            arr[i]+=value;
        }
    }

    void increaseOnline(int arr[], HashMap<Integer, Integer> offline, int currtime) {

        for(int i=0; i<arr.length; i++) {
            if(offline.containsKey(i)) {
                int prevtime = offline.get(i);
                if(currtime - prevtime >=60) {
                    arr[i]++;
                    offline.remove(i);
                }
            }
            else {
                arr[i]++;
            }
        }

    }
}
/*

Input: numberOfUsers = 2, 
events = 
[["MESSAGE","10","id1 id0"],
["OFFLINE","11","0"],
["MESSAGE","71","HERE"]]
HERE -- online users = all-offline

mentions = [2,2]

offline = [0-11] 71th

Output: [2,2]

["OFFLINE","71","0"],
["MESSAGE","71","HERE"]]

1. Events array need to be sorted on the basis of timestamp
    If t1==t2
        then process offline event first
2.  Processing all the events
    mentions[]
*/