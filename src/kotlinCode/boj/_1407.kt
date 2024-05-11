package kotlinCode.boj

import kotlin.math.ceil


fun main() = with(System.`in`.bufferedReader()) {
    // a, b <= 10^15
    val (a, b) = readLine().split(" ").map { it.toLong() }
    println(solve1407(b) - solve1407(a - 1))
}

fun solve1407(n: Long): Long {
    var sum = n
    var i = 2L
    while (i <= n) {
        sum += (n / i) * (i shr 1)
        i *= 2
    }
    return sum
}