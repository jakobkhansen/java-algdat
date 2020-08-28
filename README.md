# java-algdat
Java implementation of different algorithms and datastructures.

Preparation for being a TA in IN2010 - datastructures and algorithms at the University of Oslo.

Most algorithms and datastructures are contained in a single file, including a single test often
randomly generated. Some algorithms use datastructures where its natural, for example
`Dijkstra.java` uses `PriorityQueue.java` in its implementation.

Other files are various utilities which generate tests, graphs and help visualize the
algorithms.

## Algorithms (✔️ Implemented / ❌ Not implemented):
* Sorting
  * ✔️ Insertion sort
  * ✔️  Selection sort
  * ✔️  Merge sort
  * ✔️  Quick sort
  * ✔️  Bucket sort
  * ✔️ Radix sort
  * ✔️  Counting sort
  * ✔️ Heap sort

* Graphs
  * Traversal
    * ✔️ DFS
    * ✔️ BFS

  * Shortest path
    * ✔️ Dijkstra
    * ✔️ Bellman-Ford
    * ❌ Topological sort shortest path

  * Minimum spanning tree
    * ✔️ Prim's algorithm
    * ✔️ Kruskal's algorithm
    * ❌ Borůvka's algorithm

  * Other
    * ✔️  Topological sort
    * ✔️  Separation nodes (Tarjan)
    * ✔️  Kosaraju's algorithm (Strongly connected components)
    * ❌ Huffman coding

* Arrays
  * ✔️ Binary search

* String algorithms
    * Pattern matching
        * ✔️ Naive algorithm
        * ✔️ Horspool algorithm
        * ❌ Knut-Morris-Pratt algorithm
        * ❌ Rabin-Karp algorithm

## Datastructures
* Containers
  * ✔️ Stack
  * ✔️ Priority queue
  * ❌ Heap
  * ❌ HashMap

* Graphs
  * ✔️ Binary tree
  * ✔️ Red black tree

## Utilities
* ✔️ Graph Generator
* ✔️ Binary tree visualizer / Red black tree visualizer
* ✔️  Sorting test generator
* ❌ Binary tree generator
