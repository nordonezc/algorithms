# Recursion vs Iteration
### <b>Iteration approach</b>
* Solve recursion problems but sequentially

### <b>Recursive approach</b>
*  Stack memory store recursive calls


## Recursion types
### Tail recursion
* Call occurs at the end of the functions
* there is no unknown values in the recursive call
* C++ compiler perform tail call optimization
* Java compiler does not support tail recursion optimization

### Head recursion
* Call occurs at the beginning of the functions
* Needs more memory
* recursive call depend on previous call


## Benefits and cons
### Pros
* Solution depends on smaller instances of the same problem
* Define base cases to avoid infinite loops
* Every smaller problem can be solved in the same form

### Cons
* Head Recursion is slower because it fills the stack memory


<b>Note:</b> The information written was based on the course Recursion, Backtracking and Dynamic Programming in Java
(udemy.com/course/algorithmic-problems-in-java)