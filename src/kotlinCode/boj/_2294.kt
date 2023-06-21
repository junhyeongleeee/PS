package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

private lateinit var st: StringTokenizer
private lateinit var dp: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    dp = IntArray(k + 1) { Int.MAX_VALUE }

    dp[0] = 0

    repeat(n) { i ->
        val coin = readLine().toInt()

        for (j in coin..k) {
            if (dp[j - coin] == Int.MAX_VALUE) {
                continue
            }
            dp[j] = min(dp[j], dp[j - coin] + 1)
        }
    }

    if (dp[k] == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(dp[k])
    }
}