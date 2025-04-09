package leetcode.graphs.bfs

import leetcode.graphs.common.Node
import java.util.*

fun cloneGraph(node: Node?): Node? {
    node ?: return null

    val clone = hashMapOf<Int, Node>()
    val queue = LinkedList<Node>()

    queue.add(node)
    clone[node.value] = Node(node.value)

    // BFS
    while (queue.isNotEmpty()) {
        val current = queue.remove()
        val currentClone = clone[current.value]!!

        // clone all children
        current.children.forEach { child ->
            if (child.value !in clone) {
                clone[child.value] = Node(child.value)
                queue.add(child)
            }
            currentClone.children.add(clone[child.value]!!)
        }
    }

    return clone[node.value]
}

fun main() {
    linearGraph()
    cyclicGraph()
    nullInput()
}

private fun linearGraph() {
    println("Test 1: Linear Graph")
    val linear1 = Node(1)
    val linear2 = Node(2)
    val linear3 = Node(3)
    linear1.children.add(linear2)
    linear2.children.add(linear3)
    println("Original:")
    linear1.printGraph()
    val linearClone = cloneGraph(linear1)
    println("Clone:")
    linearClone?.printGraph()
    println()
}

private fun cyclicGraph() {
    println("Test 2: Cyclic Graph")
    val nodeOne = Node(1)
    val nodeTwo = Node(2)
    val nodeThree = Node(3)
    val nodeFour = Node(4)
    nodeOne.children = arrayListOf(nodeTwo, nodeFour)
    nodeTwo.children = arrayListOf(nodeOne, nodeThree)
    nodeThree.children = arrayListOf(nodeTwo, nodeFour)
    nodeFour.children = arrayListOf(nodeOne, nodeThree)
    println("Original:")
    nodeOne.printGraph()
    val cyclicClone = cloneGraph(nodeOne)
    println("Clone:")
    cyclicClone?.printGraph()
    println()
}

private fun nullInput() {
    println("Test 3: Null Input")
    val nullClone = cloneGraph(null)
    println("Clone: ${nullClone ?: "null"}")
}