package leetcode.graphs.common

import java.util.*

data class Node(
    val value: Int,
    var children: MutableList<Node> = mutableListOf(),
) {
    override fun toString(): String =
        StringBuilder().apply {
            append("Node: ")
            append(value)
            append(", Children: ")
            children.forEach { child ->
                append(child.value)
                append(" ")
            }
        }.toString()

    fun printGraph(type: TraverseType = TraverseType.DFS) {
        when (type) {
            TraverseType.DFS ->  dfs(this) { println(it) }
            TraverseType.BFS ->  bfs(this) { println(it) }
        }
    }

    enum class TraverseType {
        DFS,
        BFS
    }
}

fun bfs(root: Node, block: (Node) -> Unit ) {
    val queue = LinkedList<Node>()
    val visited = hashSetOf<Int>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val current = queue.remove()
        if (visited.contains(current.value)) continue

        queue.addAll(current.children)
        block(current)
        visited.add(current.value)
    }
}

fun dfs(node: Node, visited: HashSet<Int> = hashSetOf(), block: (Node) -> Unit) {
    // avoid revisit
    if (visited.contains(node.value)) return

    // visit
    block(node)
    visited.add(node.value)

    // recursive go through each child
    node.children.forEach { child ->
        dfs(child, visited, block)
    }
}