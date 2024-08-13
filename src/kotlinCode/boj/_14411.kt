package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()          // <= 1_000_000

    val arr = Array(n) { Pair(0, 0) }

    repeat(n) {
        st = StringTokenizer(readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        arr[it] = x to y
    }

    arr.sortWith(compareByDescending<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    var sum = arr[0].first.toLong() * arr[0].second
    var maxY = arr[0].second

    for (i in 1 until n) {
        if (maxY < arr[i].second) {
            sum += (arr[i].second - maxY) * arr[i].first
            maxY = arr[i].second
        }
    }
    println(sum)
}