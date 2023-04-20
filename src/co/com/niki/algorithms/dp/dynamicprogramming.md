# Dynamic programming
* Optimization technique.
* Break down complicated problems into smaller sub-problems.
* Applicable for overlapping sub-problems.
* Sub-problems solution are stored.
* Optimal solution can be constructed from optimal solutions then it has
an optimal substructure.

## Knapsack problem
* Combinatorial optimization
* Given a set of N items usually numbered from 1 to N
* Each has a mass and value, determine the number of 
each item to include in a collection so that the 
total weight is less or equal to a given limit

### Solution
* Divisible: Fractions of the given items
  * Sort NlogN
  * start with the highest and go along the list
  * NlogN + N = NLogN
* 0-1: Either take a given item or do not. DP!
  * Brute force for each item 2N
  * Maximize Sum Value of i th item times Decision to take
    then the sum of weight times item sub i is lower
    than the capacity

## Rod Cutting problem
* Given rod with certain length with given 'p' prices
* Optimal price to maximize profit
* Simple recursion: Now the result with n-1 instead of look for every combination

## Subset Sum Problem
* Determine the numbers used in a given list to retrieve the given sum
* Naive: Generate all the subsets sums. n2
* dp: pseudo-polynomial