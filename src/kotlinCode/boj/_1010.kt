package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var dp: Array<IntArray>
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    repeat(t) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        dp = Array(n + 1) { IntArray(m + 1) }

        for (i in 1 .. m) {
            dp[1][i] = i
        }

        for (i in 2 ..  n) {
            for (j in i .. m) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]
            }
        }
        sb.append("${dp[n][m]}\n")
    }
    println(sb)
}