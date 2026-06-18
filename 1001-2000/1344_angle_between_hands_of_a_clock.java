class Solution {
    public double angleClock(int hour, int minutes) {

        // Time: O(1), Space: O(1)

        // Hour hand moves 30 degrees per hour, plus 0.5 degrees per minute (drift)
        double hourdegree = (hour % 12) * 30 + (0.5) * minutes;

        // Minute hand moves 6 degrees per minute
        double mindegree = minutes * 6;

        double diff = Math.abs(hourdegree - mindegree);

        // Return the smaller of the two angles formed (clockwise vs counter-clockwise)
        return Math.min(diff, 360 - diff);
    }
}