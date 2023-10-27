package leetcode.graphs.unionfind

/**
 * https://leetcode.com/problems/number-of-provinces/
 *
 * This problem uses union find
 */
class SolutionNumberOfProvinces {

    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val length = isConnected.size

        val uf = UnionFind(length)

        for (i in isConnected.indices) {
            for (j in isConnected.indices) {
                if (isConnected[i][j] == 1)
                    uf.union(i, j)
            }
        }

        return uf.count
    }

}

fun main() {
    SolutionNumberOfProvinces().apply {
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
                    intArrayOf(1, 0, 0, 1),
                    intArrayOf(0, 1, 1, 0),
                    intArrayOf(0, 1, 1, 1),
                    intArrayOf(1, 0, 1, 1),
                )
            )
        )
    }
}