package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private var answer = 0
private val dp = IntArray(17)
private lateinit var arr: Array<Pair<Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n + 1) { Pair(0, 0) }

    repeat(n) {
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()
        val p = st.nextToken().toInt()

        arr[it + 1] = Pair(t, p)
    }

    for (i in 1..n) {
        val t = arr[i].first
        val p = arr[i].second

        val nt = i + t

        if (nt <= n + 1) {
            for (j in nt .. n + 1) {
                dp[j] = max(dp[j], dp[i] + p)
            }
        }
    }

//    println(dp.joinToString(" "))
    println(dp[n + 1])

//    println(answer)
}