package kotlinCode.boj

import kotlin.math.min

/**
 *  Four Squares
 *
 *  1 <= n <= 50,000
 */
private val dp = IntArray(50001)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    dp[1] = 1
    var s = 1
    for (i in 1 .. 50000) {
        var min = Integer.MAX_VALUE
        var j = 1
        while (j*j <= i) {
            min = min(min, dp[i - j*j])
            j++
        }
        dp[i] = min + 1
    }
    println(dp[n])
}