class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        HashSet<String> hset = new HashSet<>();
        // n, m
        // O(m)
        for(int ob[] : obstacles) {
            hset.add(ob[0]+"#"+ob[1]);
        }

        int x = 0, y=0;
        int dir[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        int position = 0; // 0=N,1=E,2=S,3=W

        // right == -1
        // position + 1 %4
        // position + 3

        int ans = 0;
        // O(n*10 )

        for(int command : commands) {
            // O(n)
            // O(n+m)
            if(command==-1) {
                position = (position+1)%4;
            } else if(command==-2) {
                position = (position+3)%4;
            }
            else {
                for(int i=0; i<command; i++) {
                    String key = (x+dir[position][0]) +"#"+ (y+dir[position][1]);
                    if(hset.contains(key))
                        break;
                    else {
                        x = x + dir[position][0];
                        y = y + dir[position][1];
                    }
                }
                ans = Math.max(ans, x*x+y*y);
            }
        }

        return ans;
    }
}