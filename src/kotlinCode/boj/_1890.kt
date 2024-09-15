package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    val arr = Array(n) { IntArray(n) }
    val dp = Array(n) { LongArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    dp[0][0] = 1

    for(i in 0 until n) {
        for(j in 0 until n) {
            if(i == n - 1 && j == n - 1) continue

            if(i + arr[i][j] < n) {
                dp[i + arr[i][j]][j] += dp[i][j]
            }
            if(j + arr[i][j] < n) {
                dp[i][j + arr[i][j]] += dp[i][j]
            }
        }
    }

    println(dp[n - 1][n - 1])
}