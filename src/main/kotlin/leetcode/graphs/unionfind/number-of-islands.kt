package leetcode.graphs.unionfind

/**
 * https://leetcode.com/problems/number-of-islands/description/
 */
class SolutionNumIslands {
    var distance = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) return 0

        val uf = UnionFindd(grid)
        val rows: Int = grid.size
        val cols: Int = grid[0].size
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == '1') {
                    for (d in distance) {
                        val x = i + d[0]
                        val y = j + d[1]
                        if (
                            x in 0 until rows &&
                            y in 0 until cols &&
                            grid[x][y] == '1'
                        ) {
                            val id1 = i * cols + j
                            val id2 = x * cols + y
                            uf.union(id1, id2)
                        }
                    }
                }
            }
        }
        return uf.count
    }

    class UnionFindd(grid: Array<CharArray>) {
        private var father: MutableList<Int>
        private var m: Int
        private var n: Int
        var count: Int = 0

        init {
            m = grid.size
            n = grid.first().size
            father = MutableList(m * n) { -1 }
            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (grid[i][j] == '1') {
                        val id = i * n + j // generate unique ID
                        father[id] = id
                        count++
                    }
                }
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
    val solution = SolutionNumIslands()

    val inputOne = arrayOf(
        charArrayOf('1','1','1','1','0'),
        charArrayOf('1','1','0','1','0'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','0','0','0'),
    )

    val inputTwo = arrayOf(
        charArrayOf('1','1','0','0','0'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','1','0','0'),
        charArrayOf('0','0','0','1','1'),
    )
    println(solution.numIslands(inputTwo))
}