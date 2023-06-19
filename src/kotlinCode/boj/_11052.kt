package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var dp: IntArray

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    dp = IntArray(n + 1)
    st = StringTokenizer(readLine())

    repeat(n) {
        dp[it + 1] = st.nextToken().toInt()
    }

    for(i in 2 .. n) {
        for (j in 1 .. i / 2) {
            dp[i] = max(dp[i], dp[j] + dp[i - j])
        }
    }

    println(dp[n])
}