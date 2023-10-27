package leetcode.graphs.unionfind

/**
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
 *
 * This problem uses union find data structure
 */
class SolutionNumberOfOperationsToMakeNetworkConnected {

    fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        val uf = UnionFind(n)
        var extraConnections = 0

        for (i in connections.indices) {
            val unionSuccess = uf.union(connections[i][0], connections[i][1])
            if (unionSuccess.not()) extraConnections++
        }

        return if (uf.count - 1 > extraConnections) return -1 else uf.count - 1
    }

}

fun main() {
    SolutionNumberOfOperationsToMakeNetworkConnected().apply {
        println("case 1")
        println(
            makeConnected(
                4,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 2),
                )
            )
        )
    }
}