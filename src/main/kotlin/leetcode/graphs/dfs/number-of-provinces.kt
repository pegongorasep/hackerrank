package leetcode.graphs.dfs

import leetcode.graphs.matrixToAdjList

class Solution {
    /**
     * https://leetcode.com/problems/number-of-provinces/
     *
     * This problem uses adjacency matrix
     */
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        var numberOfProvinces = 0
        val seen = MutableList(isConnected.size) { false }
        for (i in 0 until isConnected.size) {
            if (seen[i].not()) {
                numberOfProvinces++
                dfs(i, isConnected, seen)
            }
        }

        return numberOfProvinces
    }

    fun dfs(node: Int, graph: Array<IntArray>, seen: MutableList<Boolean>) {
        if (seen[node]) return
        seen[node] = true

        for (i in graph.indices) {
            if (graph[node][i] == 1)
                dfs(i, graph, seen)
        }
    }

    /**
     * Solution converting it to adjacency list
     */
    fun findCircleNumAdjList(isConnected: Array<IntArray>): Int {
        val adjList = isConnected.matrixToAdjList()

        println(adjList)

        var numberOfProvinces = 0
        val seen = MutableList(adjList.size) { false }
        for (i in 0 until adjList.size) {
            if (seen[i].not()) {
                numberOfProvinces++
                dfs(i, adjList, seen)
            }
        }

        return numberOfProvinces
    }

    fun dfs(node: Int, graph: List<List<Int>>, seen: MutableList<Boolean>) {
        if (seen[node]) return

        seen[node] = true
        for (i in graph[node]) {
            dfs(i, graph, seen)
        }
    }

}

fun main() { Solution().apply {
    println("case 1")
    println(
        findCircleNum(
            arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 0, 1),
            )
        )
    )

    println("case 2")
    println(
        findCircleNum(
            arrayOf(
                intArrayOf(1, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 1),
            )
        )
    )

    println("case 3")
    println(
        findCircleNum(
            arrayOf(
                intArrayOf(1,0,0,1),
                intArrayOf(0,1,1,0),
                intArrayOf(0,1,1,1),
                intArrayOf(1,0,1,1),
            )
        )
    )
} }