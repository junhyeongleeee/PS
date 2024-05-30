package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private val pq = PriorityQueue<Long>()

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(readLine())

    repeat(n) {
        pq.add(st.nextToken().toLong())
    }

    repeat(m) {
        val x = pq.poll()
        val y = pq.poll()
        pq.add(x + y)
        pq.add(x + y)
    }

    println(pq.sum())
}