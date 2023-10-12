package leetcode.graphs.dfs

import java.util.*

/**
 * https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes
 */
fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
    val adjList = MutableList<LinkedList<Int>>(n) { LinkedList() }
    val parents = MutableList<LinkedList<Int>>(n) { LinkedList() }
    val seen = MutableList(n) { false }

    // O(n) time
    println("adj list stage")
    for (edge in edges) {
        adjList[edge[0]].add(edge[1])
    }

    // O(n) time
    println("dfs stage")
    for (i in 0 until n) {
        if (seen[i].not()) dfs(i, adjList, parents, seen)
    }

    seen.indices.forEach { seen[it] = false }
    val uniqueParents = hashMapOf<Int, Unit>()
    // O(n) time
    println("find parent stage")
    for (i in 0 until n) {
        findParent(i, parents, uniqueParents, seen)
    }

    return uniqueParents.keys.toList()
}

fun findParent(
    node: Int,
    parents: List<LinkedList<Int>>,
    uniqueParents: HashMap<Int, Unit>,
    seen: MutableList<Boolean>,
) {
    if (seen[node]) return
    seen[node] = true

    if (parents[node].isNotEmpty()) {
        for (node in parents[node]) {
            findParent(node, parents, uniqueParents, seen)
        }
    } else {
        uniqueParents[node] = Unit
    }
}

fun dfs(
    node: Int,
    adjList: List<List<Int>>,
    parents: MutableList<LinkedList<Int>>,
    seen: MutableList<Boolean>,
) {
    if (seen[node]) return

    for (i in adjList[node]) {
        parents[i].add(node)
    }
    seen[node] = true
    for (i in adjList[node]) {
        dfs(node, adjList, parents, seen)
    }
}

fun main() {
    println(
        findSmallestSetOfVertices(
            5,
            listOf(
                listOf(0,1),listOf(2,1),listOf(3,1),listOf(1,4),listOf(2,4)
            )
        )
    )
}
