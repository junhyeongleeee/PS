package kotlinCode.boj

import java.util.PriorityQueue

private val pq = PriorityQueue<Int>()

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    repeat(n) {
        val s = readLine().toInt()
        pq.add(s)
    }
    var result = 0
    while (pq.size > 1) {
        val a = pq.poll()
        val b = pq.poll()
        result += a + b
        pq.add(a + b)
    }
    println(result)
}