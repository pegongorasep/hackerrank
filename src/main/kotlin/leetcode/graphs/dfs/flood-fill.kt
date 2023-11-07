package leetcode.graphs.dfs

import java.util.HashMap

/**
 * https://leetcode.com/problems/flood-fill/
 */
class SolutionFloodFill {

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val visited = hashMapOf<String, Unit>()
        dfs(sr, sc, image, visited, image[sr][sc], color)
        return image
    }

    private fun dfs(
        sr: Int,
        sc: Int,
        image: Array<IntArray>,
        visited: HashMap<String, Unit>,
        startingColor: Int,
        color: Int,
    ) {
        if (visited["$sr,$sc"] != null) return

        visited["$sr,$sc"] = Unit
        if (image[sr][sc] == startingColor) image[sr][sc] = color
        else return

        if (sr > 0) dfs(sr-1, sc, image, visited, startingColor, color)
        if (sr < image.size-1) dfs(sr+1, sc, image, visited, startingColor, color)
        if (sc > 0) dfs(sr, sc-1, image, visited, startingColor, color)
        if (sc < image[0].size-1) dfs(sr, sc+1, image, visited, startingColor, color)
    }

}

fun main() {
    SolutionFloodFill().apply {
        println(floodFill(
            arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(1,1,0),
            intArrayOf(1,0,1)
            ), sr = 1, sc = 1, color = 2
        ))
    }
}