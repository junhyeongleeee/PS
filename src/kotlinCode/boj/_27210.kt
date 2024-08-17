package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max


private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    st = StringTokenizer(readLine())

    val arr = IntArray(n)
    val dp = Array(2) { IntArray(n + 1) }

    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    var result = 0
    for (i in 1..n) {
        val a = if (arr[i - 1] == 2) -1 else 1
        val b = if (arr[i - 1] == 2) 1 else -1
        dp[0][i] = max(dp[0][i - 1] + a, a)
        dp[1][i] = max(dp[1][i - 1] + b, b)
        result = max(result, max(dp[0][i], dp[1][i]))
    }
    println(result)
}