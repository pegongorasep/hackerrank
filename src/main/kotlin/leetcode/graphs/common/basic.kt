package leetcode.graphs.common

import leetcode.graphs.toGraph


/**
 * validates adj matrix to graph works
 */
fun main() {
    linearGraph()
    cyclicGraph()
    disconnectedGraph()
    emptyGraph()
    completeGraph()
    singleNodeGraph()
}

private fun singleNodeGraph() {
    // Test 6: Single node graph
    val adjList6: Array<Array<Int>> = arrayOf(
        arrayOf()      // 0
    )
    println("Test 6: Single node graph")
    adjList6.toGraph(0).printGraph()
}

private fun completeGraph() {
    // Test 5: Complete graph (all nodes connected: 0 ↔ 1 ↔ 2)
    val adjList5 = arrayOf(
        arrayOf(1, 2), // 0 -> 1, 2
        arrayOf(0, 2), // 1 -> 0, 2
        arrayOf(0, 1)  // 2 -> 0, 1
    )
    println("Test 5: Complete graph")
    adjList5.toGraph(0).printGraph()
    println()
}

private fun emptyGraph() {
    // Test 4: Empty graph (no edges)
    val adjList4: Array<Array<Int>> = arrayOf(
        arrayOf(),     // 0
        arrayOf(),     // 1
        arrayOf()      // 2
    )
    println("Test 4: Empty graph")
    adjList4.toGraph(0).printGraph()
    println()
}

private fun disconnectedGraph() {
    // Test 3: Disconnected graph (two components: 0 ↔ 1, 2 ↔ 3)
    val adjList3 = arrayOf(
        arrayOf(1),    // 0 -> 1
        arrayOf(0),    // 1 -> 0
        arrayOf(3),    // 2 -> 3
        arrayOf(2)     // 3 -> 2
    )
    println("Test 3: Disconnected graph (requires two prints)")
    adjList3.toGraph(0).printGraph()
    adjList3.toGraph(2).printGraph()
    println()
}

private fun cyclicGraph() {
    // Test 2: Cyclic graph (0 ↔ 1 ↔ 2 ↔ 0, a triangle)
    val adjList2 = arrayOf(
        arrayOf(1, 2), // 0 -> 1, 2
        arrayOf(0, 2), // 1 -> 0, 2
        arrayOf(0, 1)  // 2 -> 0, 1
    )
    println("Test 2: Cyclic graph")
    adjList2.toGraph(0).printGraph()
    println()
}

private fun linearGraph() {
    // Test 1: Linear graph (0 -> 1 -> 2 -> 3)
    val adjList1 = arrayOf(
        arrayOf(1),    // 0 -> 1
        arrayOf(2),    // 1 -> 2
        arrayOf(3),    // 2 -> 3
        arrayOf()      // 3
    )
    println("Test 1: Linear graph")
    adjList1.toGraph(0).printGraph()
    println()
}