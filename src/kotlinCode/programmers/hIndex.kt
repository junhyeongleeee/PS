package kotlinCode.programmers

fun main() {

    val result = solution(intArrayOf(20, 0, 6, 1, 11, 8, 9, 2, 4, 3, 21, 22, 23))

    println(result)
}

fun solution(citations: IntArray): Int {
    var result = 0
    citations.sort()

    var tmp = 0
    citations.forEachIndexed { index, i ->
        while (tmp <= i) {
            println("tmp : $tmp, index : ${citations[index]}")
            if (index < tmp && tmp <= citations.size - index) {
                result = tmp
            }
            tmp++
        }
    }

    println(citations.joinToString(","))
    return result
}