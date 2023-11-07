package leetcode.graphs.dfs

/**
 * https://leetcode.com/problems/clone-graph/description/
 *
 * solution using DFS
 */
class SolutionCloneGraph {
    fun cloneGraph(node: Node?): Node? {
        node ?: return null

        val visited = hashMapOf<Int, Node>()
        dfs(node, visited)

        return visited[node.`val`]
    }

    private fun dfs(node: Node?, visited: HashMap<Int, Node>) {
        if (node == null) return

        visited[node.`val`] = Node(node.`val`)
        for (neighbor in node.neighbors) {
            if (visited[neighbor!!.`val`] == null) {
                dfs(neighbor, visited)
            }
            visited[node.`val`]!!.neighbors.add(visited[neighbor.`val`])
        }
    }

     class Node(var `val`: Int) {
          var neighbors: ArrayList<Node?> = ArrayList()
     }
}

fun main() {
    SolutionCloneGraph().apply {

        val nodeOne = SolutionCloneGraph.Node(1)
        val nodeTwo = SolutionCloneGraph.Node(2)
        val nodeThree = SolutionCloneGraph.Node(3)
        val nodeFour = SolutionCloneGraph.Node(4)

        nodeOne.neighbors = arrayListOf(nodeTwo, nodeFour)
        nodeTwo.neighbors = arrayListOf(nodeOne, nodeThree)
        nodeThree.neighbors = arrayListOf(nodeTwo, nodeFour)
        nodeFour.neighbors = arrayListOf(nodeOne, nodeThree)

        println(cloneGraph(nodeOne))
    }
}