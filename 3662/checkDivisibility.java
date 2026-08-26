// PATTERN: Digit Extraction / Digit Manipulation
//
// Whenever a problem asks us to perform operations on
// individual digits of a number, we can use:
//
//     digit = n % 10   -> extract the last digit
//     n = n / 10       -> remove the last digit
//
// APPROACH:
//
// 1. Keep the original number safe because we need it
//    later for the final divisibility check.
//
// 2. Create variables to maintain:
//      - digitSum     -> sum of all digits
//      - digitProduct -> product of all digits
//
// 3. Use a while loop to process every digit.
//
// 4. In every iteration:
//      - Extract the last digit using % 10.
//      - Add it to digitSum.
//      - Multiply it with digitProduct.
//      - Remove the last digit using / 10.
//
// 5. After processing all digits:
//      divisor = digitSum + digitProduct
//
// 6. Finally, check:
//      n % divisor == 0
//
//    If the remainder is 0 -> true
//    Otherwise             -> false
//
// KEY DSA IDEA:
//
// Number
//    ↓
// Extract digits
//    ↓
// Perform operation on each digit
//    ↓
// Build required result
//    ↓
// Check the condition
//
// TIME COMPLEXITY:
// O(log n) because we process each digit once.
//
// SPACE COMPLEXITY:
// O(1) because we use only a few variables.
class Solution {
    public boolean checkDivisibility(int n) {

        // Store the original number in temp
        // because we will modify temp while extracting digits
        int temp = n;

        // Variable to store the sum of all digits
        int digitSum = 0;

        // Variable to store the product of all digits
        // Start with 1 because multiplying by 0 would make the result 0 immediately
        int digitProduct = 1;

        // Continue until all digits of temp are processed
        while (temp > 0) {

            // Extract the last digit
            // Example: 23 % 10 = 3
            int digit = temp % 10;

            // Add the extracted digit to the digit sum
            digitSum += digit;

            // Multiply the extracted digit with the current product
            digitProduct *= digit;

            // Remove the last digit
            // Example: 23 / 10 = 2
            temp /= 10;
        }

        // The required divisor is:
        // digit sum + digit product
        int divisor = digitSum + digitProduct;
        int divisor = digitSum + digitProduct;

        // Check whether n is completely divisible by the divisor
        // If remainder is 0, return true; otherwise false
        return n % divisor == 0;
    }
}