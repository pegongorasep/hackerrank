package leetcode.graphs.bfs

import java.util.LinkedList

/**
 * https://leetcode.com/problems/properties-graph/description/
 */
fun numberOfComponents(properties: Array<IntArray>, k: Int): Int {
    val adjList: Array<MutableList<Int>> = Array(properties.size) { mutableListOf() }
    properties.forEachIndexed { index, value ->
        properties.forEachIndexed innerScope@ { index2, value2 ->
            if (index == index2) return@innerScope
            if (index2 < index) return@innerScope

            val intersect = intersect(value, value2)
            if (intersect >= k) {
                adjList[index].add(index2)
                adjList[index2].add(index)
            }
        }
    }

    var independentComponents = 0
    var visitedCount = 0
    val visited = hashSetOf<Int>()

    while (visitedCount != properties.size) {
        for (i in properties.indices) {
            val isIndependent = bfs(i, visited, adjList)
            if (isIndependent) independentComponents++
            visitedCount++
        }
    }

    return independentComponents
}

fun bfs(node: Int, visited: HashSet<Int>, adjList: Array<MutableList<Int>>): Boolean {
    if (visited.contains(node)) return false

    val queue = LinkedList<Int>()
    queue.add(node)

    while (queue.isNotEmpty()) {
        val current = queue.remove()
        if (visited.contains(current)) continue

        adjList[current].forEach { child ->
            queue.add(child)
        }
        visited.add(current)
    }
    return true
}

fun intersect(a: IntArray, b: IntArray): Int {
    var counter = 0
    val first = a.distinct()
    val second = b.distinct()
    for (value in first) {
        if (second.contains(value)) counter++
    }
    return counter
}

fun main() {
//    caseOne()
    caseTwo()
}

private fun caseTwo() {
    //[[1,1],[1,1],]
    val input = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 1),
    )
    val result = numberOfComponents(input, 2)
    println(result)
}

private fun caseOne() {
    //[[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]]
    val input = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 1),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(5, 6),
        intArrayOf(7, 7),
    )
    val result = numberOfComponents(input, 1)
    println(result)
}