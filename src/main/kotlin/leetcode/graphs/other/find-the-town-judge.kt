package leetcode.graphs.other

/**
 * https://leetcode.com/problems/find-the-town-judge/description/
 *
 * Solution using Hashmaps
 */
fun findJudge(n: Int, trust: Array<IntArray>): Int {
    if (n == 1) return 1

    val possibleJudges = HashMap<Int, Int>()
    val nonJudges = HashMap<Int, Unit>()

    for (i in trust) {
        nonJudges[i[0]] = Unit

        val judgeCandidateLikeCount = possibleJudges[i[1]] ?: 0
        possibleJudges[i[1]] = judgeCandidateLikeCount+1
    }

    for (i in possibleJudges.keys) {
        if (possibleJudges[i] == n-1 && nonJudges[i] == null) return i
    }

    return -1
}

fun main() {
    println(findJudge(3, arrayOf(intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(3, 1))))
    println(findJudge(2, arrayOf(intArrayOf(1, 2))))
    println(findJudge(1, arrayOf()))
}
