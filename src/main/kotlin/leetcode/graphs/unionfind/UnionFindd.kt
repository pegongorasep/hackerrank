package leetcode.graphs.unionfind

class UnionFind(size: Int) {
    private var father: MutableList<Int>
    var count: Int = 0

    init {
        father = MutableList(size) { -1 }
        for (i in 0 until size) {
            father[i] = i
            count++
        }
    }

    fun union(x: Int, y: Int): Boolean {
        val findX = find(x)
        val findY = find(y)
        return if (findX != findY) {
            father[findX] = findY
            count--
            true
        } else false
    }

    fun find(node: Int): Int = if (father[node] == node) node
    else find(father[node])
}
