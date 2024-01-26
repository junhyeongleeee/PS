package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
fun main() = with(br) {

    val (n, m) = readLine().split(" ").map { it.toInt() }

    val arr = Array(n) { IntArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    val dp = Array(n + 1) { Array(m) { IntArray(3) { Int.MAX_VALUE } } }

    repeat(m) { i ->
        dp[0][i].fill(0)
    }

    for (i in 1 .. n) {
        for (j in 0 until m) {
            if (j != 0) {
                dp[i][j][0] = min((min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + arr[i - 1][j]), dp[i][j][0])
            }
            dp[i][j][1] = min((min(dp[i - 1][j][0], dp[i - 1][j][2]) + arr[i - 1][j]), dp[i][j][1])
            if (j != m - 1) {
                dp[i][j][2] = min((min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + arr[i - 1][j]), dp[i][j][2])
            }
        }
    }
    println(dp[n].flatMap { it.toList() }.minOrNull())
}