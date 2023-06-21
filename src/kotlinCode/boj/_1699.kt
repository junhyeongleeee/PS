package kotlinCode.boj

import kotlin.math.min

private lateinit var dp: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    dp = IntArray(n + 1) { Integer.MAX_VALUE }

    dp[1] = 1

    var k = 1
    for (i in 1..n) {
        if (k * k == i) {
            k++
            dp[i] = 1
            continue
        }
        for (j in 1 until k) {
            dp[i] = min(dp[i], dp[i - j * j] + 1)
        }
    }

    println(dp[n])
}