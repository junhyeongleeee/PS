package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var dp: Array<IntArray>

private const val INF = 1_000_000_000
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    dp = Array(n + 1) { IntArray(k + 1) }

    for (i in 0..n) {
        dp[i][1] = 1
    }
    for (i in 1..k) {
        dp[0][k] = 1
    }

    for (i in 2..k) {
        for (l in 0..n) {
            for (j in 0..l) {
                dp[l][i] = (dp[l][i] + dp[l - j][i - 1]) % INF
            }
        }
    }

    println(dp[n][k])
}