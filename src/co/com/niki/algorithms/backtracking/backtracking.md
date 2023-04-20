# Backtracking
* It solves:
    * Constraint satisfaction problem
    * Combinatorial optimization problems
* It is much faster than brute force
* It is a form of recursion
* Depth first search is kind of backtracking
* These problems can be represented with tree structure: game tree or potential search tree
    * Backtracking traverse tree recursively
    
## Steps
1. For very node the algorithm check if the given node can be completed to a valid solution
2. If not, the whole subtree is skipped
3. Recursively enumerates all subtree of note

## N Queens problem
Placing N chess queens on an NxN chessboard so that
no two queens threaten each other.
* Brute force approach: O(N!) -> too much states
* Backtracking will be O(2^N)

Note: Gauss worked on this problem and
Dijkstra used this problem to illustrate
structured programming

### Hamiltonian Paths

* Graph: Each vertex V is connected by Edges E 
* A directed graph: Edges are pointing to another node
* A undirected graph: Edges has no direction

A hamiltonian cycle is visit each node exactly once
beginning and ending in the same node. But force has running time O(N!) 

Representation: Number Matrix NxN when 1 represent 
a connection 

<b>Dirac principle: </b> Graph is hamiltonian 
if every vertex has V/2 or greater


### Coloring problem
* No Two adjacent vertices share the same color
* The smallest number of colors needed to color 
is called chromatic number 
* Graph bipartite is equivalent to coloring problem with
2 colores
* More solutions: Radio frequency, map coloring, scheduling
#### Approaches
* Greedy: Not the best
* Backtracking: discard multiple bad states
* Sorting nodes based on degree

### Knight Tour
A knight is moving in every cell in a chessboard
* Performs better with Divide and Conquer

### Maze Problem
Get out of the maze in a given matrix with obstacles
* Solved better with Dijkstra and A* search