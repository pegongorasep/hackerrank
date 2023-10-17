package leetcode.graphs.dfs

/**
 * https://leetcode.com/problems/count-the-number-of-complete-components
 *
 * BFS with returning value and parameters passed by reference
 */
class SolutionCountTheComponents {
    fun countCompleteComponents(n: Int, edges: Array<IntArray>): Int {
        val adjList = List<MutableList<Int>>(n) { mutableListOf() }
        for (i in 0 until edges.size) {
            adjList[edges[i][0]].add(edges[i][1])
            adjList[edges[i][1]].add(edges[i][0])
        }

        println(adjList)

        var conectedComponents = 0
        val seen = MutableList(adjList.size) { false }

        for (i in 0 until adjList.size) {
            if (seen[i].not()) {
                val size = Size()
                val isConnectedGraph = isConnectedDfs(i, adjList, seen, size)
                if (isConnectedGraph && adjList[i].size == size.value - 1) conectedComponents++
            }
        }

        return conectedComponents
    }

    private fun isConnectedDfs(
        node: Int,
        graph: List<List<Int>>,
        seen: MutableList<Boolean>,
        size: Size,
    ): Boolean {
        seen[node] = true

        var isConnected = true
        for (child in graph[node]) {
            if (graph[node].size != graph[child].size) isConnected = false
            if (seen[child].not()) {
                size.value = size.value + 1
                isConnectedDfs(child, graph, seen, size)
            }
        }

        return isConnected
    }

    class Size(var value: Int = 1)
}

fun main() {
    SolutionCountTheComponents().apply {
        /*println("case 1")
        val caseOneParams = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 2),
            intArrayOf(1, 2),
            intArrayOf(3, 4),
        )
        val resultOne = countCompleteComponents(6, caseOneParams)
        println("expected: 3 - actual: $resultOne")
        println()

        println("case 2")
        val caseTwoParams = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 2),
            intArrayOf(1, 2),
            intArrayOf(3, 4),
            intArrayOf(3, 5),
        )
        val resultTwo = countCompleteComponents(6, caseTwoParams)
        println("expected: 1 - actual: $resultTwo")
        println()

        println("case 3")
        val caseThreeParams = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(2, 0),
            intArrayOf(3, 1),
            intArrayOf(3, 2),
        )
        val resultThree = countCompleteComponents(4, caseThreeParams)
        println("expected: 0 - actual: $resultThree")
        println()*/

        println("case 4")
        val caseFourParams = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(2, 0),
            intArrayOf(3, 2),
            intArrayOf(4, 3),
        )
        val resultFour = countCompleteComponents(5, caseFourParams)
        println("expected: 0 - actual: $resultFour")
        println()
    }
}