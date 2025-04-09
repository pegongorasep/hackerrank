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

fun Array<Array<Int>>.directedEdgesListToAdjList(n: Int): Array<Array<Int>> {
    // Step 1: Convert to Array<MutableList<Int>>
    val tempAdjList = Array(n) { mutableListOf<Int>() }
    forEach { edge ->
        val u = edge[0]
        val v = edge[1]
        tempAdjList[u].add(v)
    }

    // Step 2: Find max degree for inner array size
    val maxDegree = tempAdjList.maxOfOrNull { it.size } ?: 0

    // Step 3: Convert to Array<Array<Int>> with -1 as sentinel
    return Array(n) { u ->
        val neighbors = tempAdjList[u]
        Array(maxDegree) { i ->
            if (i < neighbors.size) neighbors[i] else -1
        }
    }
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