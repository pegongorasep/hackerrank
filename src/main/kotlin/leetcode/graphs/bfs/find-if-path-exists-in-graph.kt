package leetcode.graphs.bfs

import leetcode.graphs.common.Node
import leetcode.graphs.directedEdgesListToAdjList
import leetcode.graphs.toGraph
import java.util.*
import kotlin.collections.HashSet

/**
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 *
 * Solution using BFS
 */
fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val graph = HashMap<Int, MutableList<Int>>()
    for (v in 0 until n) graph[v] = mutableListOf()
    for (edge in edges) {
        graph[edge[0]]?.add(edge[1])
        graph[edge[1]]?.add(edge[0])
    }
    println("graph: $graph")

    val bfsQ = LinkedList<Int>()
    val visited = hashMapOf<Int, Unit>()
    bfsQ.add(source)
    while (bfsQ.isNotEmpty()) {
        val current = bfsQ.remove()
        println("visiting $current")

        if (visited[current] != null) continue
        if (current == destination) return true
        graph[current]?.let { bfsQ.addAll(it) }
        visited[current] = Unit
    }

    return false
}

// source is unused here
fun validPathUsingGraphRepresentation(node: Node, source: Int, destination: Int): Boolean {
    if (node.value == destination) return true

    val queue = LinkedList<Node>()
    val visited = HashSet<Int>()
    queue.add(node)

    while (queue.isNotEmpty()) {
        val current = queue.pop()
        if (current.value == destination) return true
        current.children.forEach { child ->
            if (visited.contains(child.value).not()) {
                visited.add(child.value)
                queue.add(child)
            }
        }
    }

    return false
}

fun main() {
    val input = arrayOf(arrayOf(0, 1), arrayOf(1, 2), arrayOf(2, 0))
//    println(validPath(3, input, 0, 2))

    // this is not an adj list
    val graph = input.directedEdgesListToAdjList(3).toGraph(0)
    graph.printGraph()
    val result = validPathUsingGraphRepresentation(graph, 0, 2)
    println(result)
}
