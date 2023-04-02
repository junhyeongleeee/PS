package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

/**
 *  정수 삼각형
 */
private lateinit var arr: Array<IntArray>
private lateinit var dp: Array<IntArray>
private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(n) }
    dp = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(i + 1) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    for (i in 0 until n) {
        dp[n - 1][i] = arr[n - 1][i]
    }

    for (i in (n - 2) downTo 0) {
        for (j in 0 until i + 1) {
            dp[i][j] = arr[i][j] + max(dp[i + 1][j], dp[i + 1][j + 1])
        }
    }

    println(dp[0][0])
}