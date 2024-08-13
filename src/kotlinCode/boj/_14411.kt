package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer


private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()          // <= 1_000_000

    val pq = PriorityQueue(compareByDescending<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    repeat(n) {
        st = StringTokenizer(readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        pq.add(x to y)
    }
    val firstPoint = pq.peek()
    var sum = firstPoint.first.toLong() * firstPoint.second
    var maxY = firstPoint.second

    while (pq.isNotEmpty()) {
        val point = pq.poll()
        if (maxY < point.second) {
            sum += (point.second - maxY) * point.first
            maxY = point.second
        }
    }

    println(sum)
}