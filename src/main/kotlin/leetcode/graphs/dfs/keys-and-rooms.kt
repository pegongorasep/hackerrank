package leetcode.graphs.dfs

/**
 * https://leetcode.com/problems/keys-and-rooms/
 */
fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    val seen = MutableList(rooms.size) { false }

    dfs(0, rooms, seen)

    return seen.any { !it }.not()
}

fun dfs(node: Int, graph: List<List<Int>>, seen: MutableList<Boolean>) {
    if (seen[node]) return

    seen[node] = true
    for (i in graph[node]) {
        dfs(i, graph, seen)
    }
}

fun main() {
    println(
        canVisitAllRooms(
            listOf(
                listOf(1, 3),listOf(3, 0, 1),listOf(2),listOf(0)
            )
        )
    )
}