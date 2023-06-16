package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

private lateinit var st: StringTokenizer
private lateinit var dp: Array<IntArray>
private lateinit var arr: Array<IntArray>

private val arr1 : IntArray by lazy {
    intArrayOf(-1, 0, 1)
}

private const val INF = 1_000 * 1_000

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(3) }
    dp = Array(n) { IntArray(3) }
    var result = INF

    for (i in 0 until n) {
        st = StringTokenizer(readLine())
        arr[i][0] = st.nextToken().toInt()
        arr[i][1] = st.nextToken().toInt()
        arr[i][2] = st.nextToken().toInt()
    }

    for (i in 0 until 3) {

        for (j in 0 until 3) {
            dp[0][j] = if (i == j) arr[0][j]
            else INF
        }

        for (j in 1 until n) {
            dp[j][0] = min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0]
            dp[j][1] = min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1]
            dp[j][2] = min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2]
        }

        for (j in 0 until 3) {
            if (i != j) {
                result = min(result, dp[n - 1][j])
            }
        }
    }
    println(result)
}