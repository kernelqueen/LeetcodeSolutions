class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // [gain, pass, total] 
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b)-> Double.compare(b[0], a[0]));
      
        // initializing max heap based on the max gain that we can obtain
        for(int c[] : classes) {
            pq.offer(new double[]{gain(c[0], c[1]), c[0], c[1]});
        }
      
        // adding students to classes based on the maximum gain opportunity
        while(extraStudents > 0) {
            double curr[] = pq.poll();
            double pass = curr[1], total = curr[2];
            pq.offer(new double[]{gain(pass+1, total+1), pass+1, total+1});
            extraStudents--;
        }
      
        double sum = 0;
        while(!pq.isEmpty()) {
            double curr[] = pq.poll();
            sum += curr[1]/curr[2];
        }
        return sum/classes.length; // average pass ratio
    }
  
    private double gain(double pass, double total) {
        return (pass+1)/(total+1) - pass/total;
    }

}
/*
Solution Explaination:

max heap = [gain in pass ratio]

[1+1]/[2+1] - [1]/[2] = gain

*/
