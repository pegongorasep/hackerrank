package leetcode.graphs.unionfind

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
class SolutionMostStonesRemovedWithSameRowOrColumn {

    fun removeStones(stones: Array<IntArray>): Int {
        if (stones.isEmpty() || stones[0].isEmpty()) return 0

        val uf = UnionFind(stones)
        for (i in stones.indices) {
            for (j in stones.indices) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.union(i, j)
                }
            }
        }
        return stones.size - uf.count
    }

    class UnionFind(items: Array<IntArray>) {
        private var father: MutableList<Int>
        var count: Int = 0

        init {
            father = MutableList(items.size) { -1 }
            for (i in items.indices) {
                father[i] = i
                count++
            }
        }

        fun union(nodeOne: Int, nodeTwo: Int) {
            val findOne = find(nodeOne)
            val findTwo = find(nodeTwo)
            if (findOne != findTwo) {
                father[findOne] = findTwo
                count--
            }
        }

        fun find(node: Int): Int {
            if (father[node] == node)
                return node

            return find(father[node])
        }

    }

}

fun main() {
    val solution = SolutionMostStonesRemovedWithSameRowOrColumn()
    val inputOne = arrayOf(
        intArrayOf(0,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(1,2),
        intArrayOf(2,1),
        intArrayOf(2,2),
    )

    println(solution.removeStones(inputOne))
}