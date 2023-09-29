package leetcode.graphs.dfs

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/description/
 *
 * solution with bfs
 */
fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
    val nodes = hashMapOf<Int, Node>()
    for (i in 0 until graph.size) {
        nodes[i] = Node(i, mutableListOf())
    }
    for (i in 0 until graph.size) {
        for (j in graph[i]) {
            val node = nodes[i]
            node!!.children.add(nodes[j]!!)
        }
    }

    val result = mutableListOf<MutableList<Int>>()
    dfs(nodes[0]!!, graph.size-1, listOf(), result)

    return result
}

fun dfs(node: Node, target: Int, path: List<Int>, result: MutableList<MutableList<Int>>) {
    if (node.value == target) {
        val pathh = mutableListOf<Int>().apply {
            addAll(path)
            add(node.value) }
        result.add(pathh)
        return
    }

    for (children in node.children) {
        dfs(children, target, path.plus(node.value), result)
    }

    return
}

class Node(
    val value: Int,
    val children: MutableList<Node>,
)

fun main() {
    //println(allPathsSourceTarget(arrayOf(intArrayOf(1, 2), intArrayOf(3), intArrayOf(3), intArrayOf())))
    println(allPathsSourceTarget(arrayOf(
        intArrayOf(4, 3, 1),
        intArrayOf(3, 2, 4),
        intArrayOf(3),
        intArrayOf(4),
        intArrayOf(),
    )))
}