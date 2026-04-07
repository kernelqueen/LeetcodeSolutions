class Robot {

    List<int[]> cells;
    int count;

    public Robot(int width, int height) {
        // 4*100 = T.C.
        cells = new ArrayList<>();
        count = 0;

        // [0,0] - South
        cells.add(new int[]{0,0,3});

        // 0, 1,2,3 - for E , N , W and S

        // East cells
        for(int i=1; i<width; i++)
            cells.add(new int[]{i, 0, 0});
        // North cells
        for(int j=1; j<height; j++)
            cells.add(new int[]{width-1, j, 1});
        // West cells
        for(int i=width-2; i>=0; i--)
            cells.add(new int[]{i, height-1, 2});
        // South cells
        for(int j=height-2; j>0; j--)
            cells.add(new int[]{0, j, 3});

    }
    
    public void step(int num) {
        count += num; // O(1)
    }
    
    public int[] getPos() {
        // O(1)
        int index = count%cells.size();
        int x = cells.get(index)[0];
        int y = cells.get(index)[1];
        return new int[]{x,y};

    }
    
    public String getDir() {
        // O(1)
        // count = 0, move -- East -- South
        if(count==0)
            return "East";
        int index = count%cells.size();
        int dir = cells.get(index)[2];
        if(dir==0)
            return "East";
        else if(dir==1)
            return "North";
        else if(dir==2)
            return "West";
        else return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */