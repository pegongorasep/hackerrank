package leetcode.graphs.bfs

import leetcode.graphs.printAdjList
import java.util.*

/**
 * BFS traversal
 */
fun bfsTraversal(adjList: Array<Array<Int>>, startNode: Int) {
    adjList.printAdjList()

    val bfsQ = LinkedList<Int>()
    val visited = hashSetOf<Int>()
    bfsQ.add(startNode)
    while (bfsQ.isNotEmpty()) {
        val current = bfsQ.remove()
        if (visited.contains(current)) continue

        println("visiting $current")
        bfsQ.addAll(adjList[current])
        visited.add(current)
    }

    return
}

fun main() {
    // Test 1: Linear graph (0 -> 1 -> 2 -> 3)
    val adjList1 = arrayOf(
        arrayOf(1),    // 0 -> 1
        arrayOf(2),    // 1 -> 2
        arrayOf(3),    // 2 -> 3
        arrayOf()      // 3
    )
    println("Test 1: Linear graph")
    bfsTraversal(adjList1, 0)
    println()

    // Test 2: Cyclic graph (0 ↔ 1 ↔ 2 ↔ 0, a triangle)
    val adjList2 = arrayOf(
        arrayOf(1, 2), // 0 -> 1, 2
        arrayOf(0, 2), // 1 -> 0, 2
        arrayOf(0, 1)  // 2 -> 0, 1
    )
    println("Test 2: Cyclic graph")
    bfsTraversal(adjList2, 0)
    println()

    // Test 3: Disconnected graph (two components: 0 ↔ 1, 2 ↔ 3)
    val adjList3 = arrayOf(
        arrayOf(1),    // 0 -> 1
        arrayOf(0),    // 1 -> 0
        arrayOf(3),    // 2 -> 3
        arrayOf(2)     // 3 -> 2
    )
    println("Test 3: Disconnected graph")
    bfsTraversal(adjList3, 0)
    println()

    // Test 4: Empty graph (no edges)
    val adjList4: Array<Array<Int>> = arrayOf(
        arrayOf(),     // 0
        arrayOf(),     // 1
        arrayOf()      // 2
    )
    println("Test 4: Empty graph")
    bfsTraversal(adjList4, 0)
    println()

    // Test 5: Complete graph (all nodes connected: 0 ↔ 1 ↔ 2)
    val adjList5 = arrayOf(
        arrayOf(1, 2), // 0 -> 1, 2
        arrayOf(0, 2), // 1 -> 0, 2
        arrayOf(0, 1)  // 2 -> 0, 1
    )
    println("Test 5: Complete graph")
    bfsTraversal(adjList5, 0)
    println()

    // Test 6: Single node graph
    val adjList6: Array<Array<Int>> = arrayOf(
        arrayOf()      // 0
    )
    println("Test 6: Single node graph")
    bfsTraversal(adjList6, 0)
}
