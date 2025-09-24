class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        // base case
        if(numerator==0)
            return "0";
        StringBuilder result = new StringBuilder();

        // managing signs
    
        if((numerator < 0) ^ (denominator<0)) {
            result.append("-");
        }

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // Integer part 
        result.append(num/den);

        

        long remainder = num%den;

        if(remainder==0)
            return result.toString(); // 4/2, 60/3 -- rem = 0
        
        // decimal part

        result.append(".");

        Map<Long, Integer> map = new HashMap<>();
        // Ex - 4/333, result = "0.(012)"
        // Map [[4 -> 2], [40 -> 3], [67 --> 4]]

        // remiander     = 4 --> 40 --> 67 --> 4 
        // rem*10/den    = 0 --> 1  --> 2 

        // 0-9 for remainder

        // Time and Space complexity = O(denominator)
        while(remainder > 0) {
           if(map.containsKey(remainder)) {
            int idx = map.get(remainder); // index in the string for corresponding value
            result.insert(idx, "(");
            result.append(")");
            break;
           }

            map.put(remainder, result.length());
            remainder = remainder*10;
            result.append(remainder/den); // 400/333 = 1
            remainder = remainder%den; // 67
        }

        return result.toString();

    }
}

/*
Why String?

1/997 = "0.(0010030090270812437311935807422266800401203610832497492477432296890672016048144433299899699097291875626880641925777331995987963891675025075225677031093279839518555667)"


Three cases:
1. Numbers are completely divisible (2/1 = 2), remainder is 0 at first attempt
2. Decimal value in division (1/2 = 0.5), keep dividing until remainder becomes 0
// "0.5"


3. Repetition of numbers after decimal (1/3 = 0.(3))
// 0.333333333.... = 0.(3)


Another example: 1/6 = 0.1666666 = 0.1(6)



Why use long?

Range of int:

    min = -2,147,483,648
    max = 2,147,483,647

and in case of division by min value, we may encounter overflow.

Ex: -1/-2,147,483,648





-----Repetition Case -------
Ex - 1
Fraction: 1/6
String : "0.16"
Final formatted result: 0.1(6)

remainder → index in string for the calculated digit
Map [[1 -> 2], [4 -> 3]]

remiander            = 1 --> 4 --> 4 
rem*10/den(value)    = 1 --> 6 --> 6 


Second Example
4/333

String = "0.012"
Final formatted result: 0.(012)


remainder to index of string for the calculated value
Map [[4 -> 2], [40 -> 3], [67 --> 4]]

remiander     = 4 --> 40 --> 67 --> 4 
rem*10/den    = 0 --> 1  --> 2 

*/