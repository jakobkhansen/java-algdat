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
  * ✔️ Selection sort
  * ❌ Merge sort
  * ❌ Quick sort
  * ❌ Bucket sort
  * ❌ Radix sort
  * ✔️ Counting sort
  
* Graphs
  * Traversal
    * ✔️ DFS
    * ✔️ BFS
    
  * Shortest path
    * ✔️ Dijkstra
    * ✔️ Bellman-Ford
    * ❌ Topological sort shortest path
    
  * Other
    * ❌ Topological sort
    * ❌ Separation nodes
    * ❌ Strongly connected components
* Arrays
  * ✔️ Binary search

## Datastructures
* Containers
  * ❌ Stack
  * ✔️ Priority queue
  * ❌ Heap
  * ❌ HashMap
* Graphs
  * ✔️ Binary tree
  * ❌ Red black tree

## Utilities
* ✔️ Graph Generator
* ✔️ Binary tree visualizer
* ❌ Binary tree generator
