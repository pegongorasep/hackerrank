package leetcode.graphs.bfs

import java.util.*

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

fun main() {
    println(validPath(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)), 0, 2))
}
