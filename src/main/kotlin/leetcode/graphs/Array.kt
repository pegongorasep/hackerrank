package leetcode.graphs

fun Array<IntArray>.matrixToAdjList(): MutableList<MutableList<Int>> {
    val adjList = MutableList<MutableList<Int>>(this.size) { mutableListOf() }
    for (i in 0 until this.size) {
        for (j in i + 1 until this.size) {
            if (this[i][j] == 1) {
                adjList[i].add(j)
                adjList[j].add(i)
            }
        }
    }
    return adjList
}
