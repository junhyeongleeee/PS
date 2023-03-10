package kotlinCode.boj

import java.util.*
import kotlin.math.abs

/**
 *  절대값 힙
 *
 *  x가 0이 아니면 추가
 *  0 이면 배열에서 절댓값이 가장 작은 값을 출력하고 제거
 */
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val pq = PriorityQueue<Int> { a, b ->
        if (abs(a) > abs(b)) {
            abs(a) - abs(b)
        }else if(abs(a) == abs(b)) {
            a - b
        }else {
            -1
        }
    }
    repeat(n) {
        val x = readLine().toInt()
        when (x) {
            0 -> {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n")
                } else {
                    sb.append(pq.poll()).append("\n")
                }
            }
            else -> {
                pq.add(x)
            }
        }
    }
    println(sb)
}