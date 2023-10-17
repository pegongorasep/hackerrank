package leetcode.graphs.bfs

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 *
 * Solution using BFS
 */
class SolutionMinReorder {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val adjList = List<MutableList<Pair<Int, Boolean>>>(n) { mutableListOf() }
        for (i in connections) {
            adjList[i[0]].add(Pair(i[1], true))
            adjList[i[1]].add(Pair(i[0], false))
        }

        val stack = LinkedList<Pair<Int, Boolean>>()
        val counter = Counter(0)
        val seen = HashMap<Int, Unit>()
        stack.add(Pair(0, false))
        bfs(adjList, stack, counter, seen)

        return counter.value
    }

    fun bfs(
        adjList: List<MutableList<Pair<Int, Boolean>>>,
        stack: LinkedList<Pair<Int, Boolean>>,
        counter: Counter,
        seen: HashMap<Int, Unit>
    ) {
        while (stack.isNotEmpty()) {
            val node = stack.poll()
            seen[node.first] = Unit

            if (node.second)
                counter.value = counter.value + 1
            for (i in adjList[node.first])
                if (seen[i.first] == null)
                    stack.add(i)
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
