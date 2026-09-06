# Pairwise Bitwise AND Sum

## Problem

Given an integer array `arr[]`, calculate the sum of bitwise AND for **all pairs** `(i, j)` where:

```text
i < j
```

For example:

```text
arr = [5, 10, 15]
```

The pairs are:

```text
5 & 10  = 0
5 & 15  = 5
10 & 15 = 10
```

Therefore:

```text
0 + 5 + 10 = 15
```

---

## 1. Brute Force Approach

The most obvious solution is to generate every pair:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        ans += arr[i] & arr[j];
    }
}
```

However, this takes:

```text
O(n²)
```

There can be approximately:

```text
n * (n - 1) / 2
```

pairs.

With:

```text
n <= 100000
```

this is far too slow.

So we need to avoid explicitly generating the pairs.

---

# 2. Important Bitwise AND Observation

For two numbers:

```text
A & B
```

a particular bit will be `1` **only when that bit is set in both A and B**.

For example:

```text
5  = 0101
15 = 1111

5 & 15
   = 0101
   = 5
```

Look at each bit independently.

Therefore, instead of calculating:

```text
A & B
```

for every pair, we can calculate the contribution of **each bit** to the final answer.

This is called the:

> **Bit Contribution / Bit Counting technique**

---

# 3. Counting Pairs for One Bit

Suppose we are currently checking bit `b`.

Assume `k` elements in the array have this bit set to `1`.

For this bit to appear in:

```text
arr[i] & arr[j]
```

both elements must have the bit set.

So we need to choose **any two elements** from those `k` elements.

The number of such pairs is:

```text
k * (k - 1) / 2
```

This is simply:

```text
kC2
```

Every such pair contributes:

```text
2^b
```

to the answer.

Therefore:

```text
Contribution of bit b
=
(k * (k - 1) / 2) * 2^b
```

---

# 4. Example

Consider:

```text
arr = [5, 10, 15]
```

Binary representation:

```text
5  = 0101
10 = 1010
15 = 1111
```

### Bit 0

Bit 0 is set in:

```text
5, 15
```

So:

```text
k = 2
```

Number of pairs:

```text
2 * 1 / 2 = 1
```

Bit value:

```text
2^0 = 1
```

Contribution:

```text
1 * 1 = 1
```

---

### Bit 1

Bit 1 is set in:

```text
10, 15
```

Again:

```text
k = 2
pairs = 1
```

Bit value:

```text
2^1 = 2
```

Contribution:

```text
1 * 2 = 2
```

---

### Bit 2

Bit 2 is set in:

```text
5, 15
```

Therefore:

```text
pairs = 1
```

Bit value:

```text
2^2 = 4
```

Contribution:

```text
4
```

---

### Bit 3

Bit 3 is set in:

```text
10, 15
```

Therefore:

```text
pairs = 1
```

Bit value:

```text
2^3 = 8
```

Contribution:

```text
8
```

---

### Final Answer

```text
1 + 2 + 4 + 8 = 15
```

---

# 5. Understanding the Code

```java
class Solution {
    public long pairAndSum(int[] arr) {

        int n = arr.length;
        long ans = 0;

        for (int bit = 0; bit < 31; bit++) {

            long count = 0;

            for (int num : arr) {

                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            ans += count * (count - 1) / 2 * (1L << bit);
        }

        return ans;
    }
}
```

Let's understand every important part.

---

## 6. Checking Every Bit

```java
for (int bit = 0; bit < 31; bit++)
```

The maximum value of `arr[i]` is:

```text
10^8
```

which fits within 31 bits.

We process each bit independently.

---

## 7. Counting Numbers Having the Current Bit

```java
long count = 0;

for (int num : arr) {
    if ((num & (1 << bit)) != 0) {
        count++;
    }
}
```

This expression:

```java
num & (1 << bit)
```

checks whether the current bit is set in `num`.

For example:

```text
num = 5

5 = 0101
```

For bit `2`:

```text
1 << 2 = 0100
```

Then:

```text
0101
&
0100
----
0100
```

The result is non-zero, so bit `2` is set.

Therefore we increment:

```java
count++;
```

---

# 8. Calculating Number of Pairs

After traversing the array, suppose:

```text
count = k
```

elements contain the current bit.

The number of pairs is:

```java
count * (count - 1) / 2
```

This represents:

```text
kC2
```

because we need two elements having this bit set.

---

# 9. Adding the Bit's Contribution

```java
ans += count * (count - 1) / 2 * (1L << bit);
```

There are three parts here:

### Number of pairs

```java
count * (count - 1) / 2
```

### Value of the current bit

```java
1L << bit
```

For example:

```text
bit = 0 → 1
bit = 1 → 2
bit = 2 → 4
bit = 3 → 8
```

### Total contribution

```text
number of pairs × bit value
```

We add this contribution to `ans`.

---

# 10. Why `long`?

The method returns:

```java
long
```

so we use:

```java
long ans
```

and:

```java
1L << bit
```

Using `1L` ensures the bit calculation is performed using a `long`.

This also prevents unnecessary integer overflow during the calculation.

---

# 11. Complexity

There are at most 31 bits.

For every bit, we traverse the entire array.

Therefore:

```text
Time Complexity:
O(31 × n)
```

Since `31` is a constant:

```text
O(n)
```

Space:

```text
O(1)
```

We only use a few variables regardless of the input size.

---

# 12. Pattern to Remember

This problem belongs to the:

## Bit Contribution Pattern

Whenever you see something like:

```text
Sum of AND of all pairs
Sum of OR of all pairs
Sum involving bits
```

and the constraints make `O(n²)` impossible, think:

```text
Pairwise operation
        ↓
Break it into individual bits
        ↓
Count elements satisfying the bit condition
        ↓
Count valid pairs
        ↓
Calculate bit contribution
        ↓
Add to answer
```

For **AND**:

```text
Both numbers must have the bit = 1
```

So:

```text
count = number of elements having bit
pairs = countC2
contribution = pairs × 2^bit
```

The core formula is:

```text
answer += C(count, 2) × 2^bit
```

---

## Key Takeaway

We never actually calculate:

```text
arr[i] & arr[j]
```

for every pair.

Instead, we ask:

> **For each bit, how many pairs will have this bit set in their AND?**

That transforms an `O(n²)` pair problem into an `O(n)` bit-counting solution.

# Link of Query

https://www.geeksforgeeks.org/problems/sum-of-products5049/1
