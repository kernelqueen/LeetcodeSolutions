// ---------------------------------------------------------
// Approach 1: Simulation / Greedy Iteration
// ---------------------------------------------------------
// Idea:
// - Keep track of "filled" bottles available to drink and "empty" bottles after drinking.
// - Every time we finish drinking all filled bottles, we increase the count of empty ones.
// - If we have enough empty bottles to exchange (>= exchange cost), we get 1 new filled bottle.
// - After each exchange, the required "exchange cost" increases by +1.
// - Repeat until we cannot get any more filled bottles.
// ---------------------------------------------------------
// Time Complexity: O(filled + exchange) in worst case
// Space Complexity: O(1)
// ---------------------------------------------------------

class Solution {
    public int maxBottlesDrunk(int filled, int exchange) {
        int ans = 0;     // total bottles drunk
        int empty = 0;   // empty bottles collected after drinking

        while (filled > 0) {
            ans += filled;     // drink all filled bottles
            empty += filled;   // add them to empty count
            filled = 0;        // no filled bottles left

            // Try exchanging empty bottles if possible
            if (empty >= exchange) {
                filled = 1;          // we get 1 new filled bottle
                empty -= exchange;   // spent 'exchange' empty bottles
            }

            exchange++;  // exchange cost increases after each trade
        }

        return ans;
    }
}
// ---------------------------------------------------------
// Approach 2: Mathematical (Formula + Quadratic Equation)
// ---------------------------------------------------------
// Idea:
// - Instead of simulating each exchange, we can calculate the maximum
//   number of exchanges (t) directly using inequalities.
// - The condition to perform t exchanges comes from the requirement that
//   we must always have enough empty bottles at each step.
//
// Derivation (simplified):
//   n = numBottles, e = numExchange
//   Inequality: t^2 + (2e - 3)t + 2 <= 2n
//   Solve for maximum integer t using quadratic formula.
//
// Steps:
// - Use quadratic root formula to approximate maximum t.
// - Take floor(root) and adjust by verifying inequality to ensure correctness.
// - Final answer = n (initial bottles) + t (exchanges).
// ---------------------------------------------------------
// Time Complexity: O(1)  (direct formula evaluation)
// Space Complexity: O(1)
// ---------------------------------------------------------

class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        double n = numBottles;
        double e = numExchange;

        // Coefficient b from inequality
        double b = 2 * e - 3;

        // Discriminant part of quadratic: (2e-3)^2 + 8(n-1)
        double rootval = b * b + 8 * (n - 1);

        // Positive root of quadratic equation
        double root = (-b + Math.sqrt(rootval)) / 2.0;

        // Floor to get integer candidate for t
        double t = Math.floor(root);

        // Validate inequality: t^2 + (2e - 3)t + 2 <= 2n
        if (t > 0 && (t * t + (2 * e - 3) * t + 2 > 2 * n)) {
            t--; // adjust if overshot
        }

        // Final answer = initial bottles + valid exchanges
        return (int) (numBottles + t);
    }
}
