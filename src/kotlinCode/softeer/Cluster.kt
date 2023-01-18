package kotlinCode.softeer

import java.util.*
import kotlin.math.*

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer

/**
 *   - 정렬
 *   - 가장 낮은 수 부터 하나씩 높이면서 확인
 */

fun main() = with(br) {
    val (N, b) = readLine().split(" ").map { it.toLong() }
    val n = N.toInt()
    st = StringTokenizer(readLine())

    val computers = Array(n) { 0 }

    repeat(n) {
        val num = st.nextToken().toInt()
        computers[it] = num
    }

    computers.sort()

    // 1, 1, 2, 2, 3, 6, 8, 9, 10
    var left = computers[0].toLong()
    var right = computers[n - 1] + sqrt(b.toDouble()).toLong()
    var result = 0L
    while (left <= right) {
        val mid = (left + right) / 2
        if (search(mid, computers, b)) {
            left = mid + 1
            result = mid
        } else {
            right = mid - 1;
        }
    }
    println(result)
}

fun search(min: Long, computers: Array<Int>, b: Long): Boolean {
    var cost = 0L
    for (c in computers) {
        if (c < min) {
            cost += (min - c).toDouble().pow(2.0).toLong()
            if(cost > b) return false
        }
    }
    return true
}