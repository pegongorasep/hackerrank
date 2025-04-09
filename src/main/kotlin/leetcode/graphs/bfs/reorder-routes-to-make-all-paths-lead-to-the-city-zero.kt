package leetcode.graphs.bfs

import java.util.*

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 *
 * Solution using BFS
 */
class SolutionMinReorder {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        // build a directed adjacency list (two-way)
        val adjList = List<MutableList<Pair<Int, Boolean>>>(n) { mutableListOf() }
        for (i in connections) {
            adjList[i[0]].add(Pair(i[1], true))
            adjList[i[1]].add(Pair(i[0], false))
        }

        val queue = LinkedList<Pair<Int, Boolean>>()
        val visited = HashSet<Int>()
        queue.add(Pair(0, false))

        val counter = Counter(0)
        bfs(adjList, queue, counter, visited)

        return counter.value
    }

    private fun bfs(
        adjList: List<MutableList<Pair<Int, Boolean>>>,
        queue: LinkedList<Pair<Int, Boolean>>,
        counter: Counter,
        visited: HashSet<Int>
    ) {
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            visited.add(node.first)

            // every time we find a road directed the other way increase counter
            if (node.second) counter.value += 1
            for (i in adjList[node.first])
                if (visited.contains(i.first).not())
                    queue.add(i)
        }
    }

    class Counter(var value: Int)
}

fun main() {
    val solution = SolutionMinReorder()
    val input: Array<IntArray> = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(4, 0),
        intArrayOf(4, 5),
    )
    println(solution.minReorder(6, input))
}
