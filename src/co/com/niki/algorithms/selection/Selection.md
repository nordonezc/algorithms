# Selection Algorithms
Find the smallest or largest in data structure. 
* Sorting could be an inefficient but can be reduced to this. Although, 
if it is required more than one statistics
* It can be used a data structure like binary tree or heaps, 
but it requires more memory
* We don't have the whole input at the beginning
## Quickselect
O(N) in the worst case would be O(N2)
1. Choose random pivot
2. Partition the array based on the pivot ordering
on one side the lower, and the higher on the other side
3. Return position of the pivot and take just one of the sides
### Median Quick select
Selecting the median ensure the Quick select to be O(N)
ensuring that they will have same amounted of items on both
sides. Request more memory with the median of median approach
1. Use insertion sort on split the original array. 5 is enough
2. Calculate median of each chunk
3. Calculate median of each median
## Introselect
 The hybrid algorithm which starts with quick select and then 
 falls back to median of medians if progress is too slow
## Secretary problem
how to find the K th order statistics of a stream without 
the entire input. We don't have all the data at once.
* Problem of Optimal Stopping theory
* Select the best applicant without interview every applicant
* Check Odds algorithm

<b>Solution:</b>
Discard the first n/e inputs and then select the one who 
are better than all the applicants interviewed