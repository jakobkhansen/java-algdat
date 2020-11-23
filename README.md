# java-algdat
Java implementation of different algorithms and datastructures.

Preparation for being a TA in IN2010 - datastructures and algorithms at the University of Oslo.

Most algorithms and datastructures are contained in a single file, including a single test often
randomly generated. Some algorithms use datastructures where its natural, for example
`Dijkstra.java` uses `PriorityQueue.java` in its implementation.

Other files are various utilities which generate tests, graphs and help visualize the
algorithms.

## Algorithms:
* Sorting
  - [x] Insertion sort
  - [x] Selection sort
  - [x] Merge sort
  - [x] Quick sort
  - [x] Bucket sort
  - [x] Radix sort
  - [x] Counting sort
  - [x] Heap sort

* Graphs
  * Traversal
    - [x] DFS
    - [x] BFS
    - [x] Iterative deepening

  * Shortest path
    - [x] Dijkstra
    - [x] Bellman-Ford
    - [ ] Topological sort shortest path

  * Minimum spanning tree
    - [x] Prim's algorithm
    - [x] Kruskal's algorithm
    - [ ] Borůvka's algorithm

  * Other
    - [x]  Topological sort
    - [x]  Separation nodes (Tarjan)
    - [x]  Kosaraju's algorithm (Strongly connected components)
    - [x] Huffman coding
    - [ ] Maximal matching (Hungarian)
    - [ ] Maximum flow in network (Ford-Fulkerson)

* Arrays
  - [x] Binary search

* String algorithms
    * Pattern matching
        - [x] Naive algorithm
        - [x] Horspool algorithm
        - [ ] Knut-Morris-Pratt algorithm
        - [ ] Rabin-Karp algorithm
    * Edit distance
        - [x] Standard Bottom-up (Wagner-Fischer)
        - [x] Top-down memoized (Wagner-Fischer?)

## Datastructures
* Containers
  - [x] Stack
  - [x] Priority queue
  - [x] Binary Heap
  - [ ] Leftist Heap
  - [ ] Binomial Heap
  - [ ] Fibonacci Heap
  - [ ] HashMap

* Graphs
  - [x] Binary tree
  - [x] Red black tree
  - [x] AVL tree

## Utilities
- [x] Graph Generator
- [x] Binary tree visualizer / Red black tree visualizer
- [x]  Sorting test generator
- [ ] Binary tree generator
