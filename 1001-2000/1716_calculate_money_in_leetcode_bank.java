class Solution {
    public int totalMoney(int n) {
        // Number of complete weeks (each week has 7 days)
        int weeks = n / 7;
        // Remaining days after full weeks
        int days = n % 7;

        int money = 0;

        // Total from all full weeks:
        // Week 1 sum = 28 (1+2+...+7)
        // Week 2 adds +7 more, Week 3 adds +14 more, etc.
        // So the total for all weeks = 28*weeks + 7*(0+1+2+...+(weeks-1))
        // = 28*weeks + 7*(weeks-1)*weeks/2
        money = 28 * weeks + 7 * (weeks - 1) * weeks / 2;

        // Remaining days (in an incomplete week):
        // They start from (weeks+1) on Monday, (weeks+2) on Tuesday, etc.
        // Sum of first 'days' natural numbers = days*(days+1)/2
        // Plus add 'weeks' to each day since each week starts higher
        // So: (days*(days+1))/2 + days*weeks
        money += days * (days + 1) / 2 + days * weeks;

        return money;
    }
}

/*
Pattern Example:

Mon  Tue  Wed  Thu  Fri  Sat  Sun
1,   2,   3,   4,   5,   6,   7   -> Week 1 = 28
2,   3,   4,   5,   6,   7,   8   -> Week 2 = 28 + 7
3,   4,   5,   6,   7,   8,   9   -> Week 3 = 28 + 7 + 7
...

Full weeks contribution = 28*weeks + 7*(0+1+2+...+(weeks-1))
Partial week contribution = (1+2+...+days) + weeks*days
*/
