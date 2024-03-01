package kotlinCode.boj

import java.util.StringTokenizer

/**
 *
 */

private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()){

    val dp = Array(21) { Array(21) { IntArray(21) } }

    // a, b, c 는 하나라도 0 이면 1

    for (i in 0 .. 20) {
        for (j in 0 .. 20) {
            dp[0][i][j] = 1
            dp[i][j][0] = 1
            dp[i][0][j] = 1
        }
    }

    for (i in 1 ..  20) {
        for (j in 1 .. 20) {
            for (k in 1 .. 20) {

                dp[i][j][k] = if (i < j && j < k) {
                    dp[i][j][k - 1] + dp[i][j - 1][j - 1] - dp[i][j - 1][k]
                }else {
                    dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1]
                }
            }
        }
    }


    while (true) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }

        if (a == -1 && b == -1 && c == -1) break

        val result = when{
            a <= 0 || b <= 0 || c <= 0 -> {
                1
            }
            a > 20 || b > 20 || c > 20 -> {
                dp[20][20][20]
            }
            else -> {
                dp[a][b][c]
            }
        }
        sb.append("w($a, $b, $c) = $result\n")
    }

    println(sb)
}