package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

private lateinit var st: StringTokenizer

data class Hotel(val cost: Int, val count: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    val c = st.nextToken().toInt()          // <= 10_000
    val n = st.nextToken().toInt()          // <= 20

    val list = mutableListOf<Hotel>()
    val dp = IntArray(c + 101) { 1_000_000 }
    dp[0] = 0

    repeat(n) {
        st = StringTokenizer(readLine())

        val cost = st.nextToken().toInt()   // <= 100
        val count = st.nextToken().toInt()

        list.add(Hotel(cost, count))
    }
    for (h in list) {
        for (i in h.count..c + 100) {
            dp[i] = min(h.cost + dp[i - h.count], dp[i])
        }
    }

    println(dp.filterIndexed { index, _ -> index in c..(c + 100) }.minOf { it })
}