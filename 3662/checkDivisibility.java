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

        // Check whether n is completely divisible by the divisor
        // If remainder is 0, return true; otherwise false
        return n % divisor == 0;
    }
}