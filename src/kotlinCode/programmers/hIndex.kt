package kotlinCode.programmers

fun main() {

    val result = solution(intArrayOf(20, 0, 6, 1, 11, 8, 9, 2, 4, 3, 21, 22, 23))

    println(result)
}

fun solution(citations: IntArray): Int {
    citations.sort()

    val result = citations.sortedArrayDescending()

    println(result.joinToString(","))

    for (i in result.indices) {
        if (result[i] < i + 1) {
            return i
        }
    }

    return 0
}