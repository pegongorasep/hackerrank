package leetcode.graphs

import leetcode.graphs.common.Node

fun Array<IntArray>.matrixToAdjList(): MutableList<MutableList<Int>> {
    val adjList = MutableList<MutableList<Int>>(this.size) { mutableListOf() }
    for (i in indices) {
        for (j in i + 1 until this.size) {
            if (this[i][j] == 1) {
                adjList[i].add(j)
                adjList[j].add(i)
            }
        }
    }
    return adjList
}

fun Array<Array<Int>>.printAdjList() {
    println("adjList:")
    forEachIndexed { index, value ->
        print("$index: ")
        value.forEach {
            print("$it ")
        }
        println()
    }
    println()
}

// TODO remove duplication
fun Array<Array<Int>>.toGraph(root: Int): Node {
    val nodes = HashMap<Int, Node>()
    this.forEachIndexed { index, value ->
        nodes[index] = Node(index)
    }
    forEachIndexed { index, values ->
        val node = nodes[index]!!
        values.forEach { child ->
            node.children.add(nodes[child]!!)
        }
    }

    return nodes[root]!!
}

fun Array<IntArray>.toGraph(root: Int = 0): Node {
    val nodes = HashMap<Int, Node>()
    this.forEachIndexed { index, value ->
        nodes[index] = Node(index)
    }
    forEachIndexed { index, values ->
        val node = nodes[index]!!
        values.forEach { child ->
            node.children.add(nodes[child]!!)
        }
    }

    return nodes[root]!!
}