package leetcode.graphs.other

/**
 * https://leetcode.com/problems/find-center-of-star-graph/
 *
 * Solution using ifs
 */
fun findCenter(edges: Array<IntArray>): Int {
    val firstEdge = edges[0]
    val secondEdge = edges[1]

    if (firstEdge[0] == secondEdge[0]) return firstEdge[0]
    if (firstEdge[0] == secondEdge[1]) return firstEdge[0]
    if (firstEdge[1] == secondEdge[0]) return firstEdge[1]
    if (firstEdge[1] == secondEdge[1]) return firstEdge[1]

    return 0
}

fun main() {
    println(findCenter(arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(4,2))))
}