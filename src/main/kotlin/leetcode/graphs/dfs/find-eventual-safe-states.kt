package leetcode.graphs.dfs

/**
 * https://leetcode.com/problems/find-eventual-safe-states/
 *
 * solution with dfs
 */
class SolutionEventualSafeNodes {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val nodeMap = hashMapOf<Int, Int>()
        val seen = hashMapOf<Int, Unit>()
        val safeNodes = mutableListOf<Int>()

        for (node in graph.indices) {
            if (nodeMap[node] == null)
                if (dfs(node, graph, seen, nodeMap, safeNodes)) {
                    nodeMap[node] = 1
                    safeNodes.add(node)
                } else {
                    nodeMap[node] = 2
                }

        }

        return safeNodes.distinct().sorted()
    }

    private fun dfs(
        node: Int,
        adjList: Array<IntArray>,
        seen: HashMap<Int, Unit>,
        nodeMap: HashMap<Int, Int>,
        safeNodes: MutableList<Int>,
    ): Boolean {
        seen[node] = Unit

        for (child in adjList[node]) {
            if (adjList[child].isEmpty() || nodeMap[child] == 1) { // safe node
                nodeMap[child] = 1
                safeNodes.add(child)
            } else if (seen[child] != null || nodeMap[child] == 2) { // unsafe node
                nodeMap[node] = 2
                return false
            } else {
                val isSafe = dfs(child, adjList, seen, nodeMap, safeNodes)
                if (isSafe) {
                    nodeMap[child] = 1
                    safeNodes.add(child)
                } else {
                    nodeMap[child] = 2
                    return false
                }
            }
        }

        return true
    }
}

fun main() {
    val solution = SolutionEventualSafeNodes()

    val input = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(5),
        intArrayOf(0),
        intArrayOf(5),
        intArrayOf(),
        intArrayOf(),
    )
    println(solution.eventualSafeNodes(input))
}