package kotlinCode.boj

import java.util.*

/**
 *  최대 힙
 *
 */

private val br = System.`in`.bufferedReader()
private val sb = StringBuilder()
private val pq = PriorityQueue<Int>(Collections.reverseOrder())

fun main() = with(br){
    val n = readLine().toInt()

    repeat(n) {
        when(val num = readLine().toInt()) {
            0 -> {
                val v = if (pq.isEmpty()) 0 else pq.poll()
                sb.append(v).append("\n")
            }
            else -> {
                pq.add(num)
            }
        }
    }
    println(sb)
}