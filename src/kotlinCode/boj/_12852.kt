package kotlinCode.boj

import kotlin.math.min


/**
 *  1 <= N <= 1_000_000
 *
 *   - X가 3으로 나누어 떨어지면, 3으로 나눔
 *   - X가 2로 나누어 떨어지면, 2로 나눔
 *   - 1을 뺌
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val dp = IntArray(1_000_001)
    val prev = IntArray(1_000_001)

    for (i in 2..1_000_000) {
        var v = Int.MAX_VALUE
        var idx = 0

        if (i % 2 == 0) {
            if (v > dp[i / 2] + 1) {
                v = dp[i / 2] + 1
                idx = i / 2
            }
        }
        if (i % 3 == 0) {
            if (v > dp[i / 3] + 1) {
                v = dp[i / 3] + 1
                idx = i / 3
            }
        }
        if (v > dp[i - 1] + 1) {
            v = dp[i - 1] + 1
            idx = i - 1
        }
        v = min(v, dp[i - 1] + 1)
        dp[i] = v
        prev[i] = idx
    }

    var index = prev[n]
    val sb = StringBuilder()
    sb.append("$n ")
    while (index != 0) {
        sb.append("$index ")
        index = prev[index]
    }

    println(dp[n])
    println(sb)
}