package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

private lateinit var st: StringTokenizer
private val dq = java.util.ArrayDeque<Int>()
fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }

    st = StringTokenizer(readLine())

    repeat(n) { dq.add(it + 1) }

    var answer = 0

    repeat(m) { i ->
        answer += findElement(st.nextToken().toInt())
    }

    println(answer)
}
fun findElement(e: Int): Int {
    var cnt = 0
    val size = dq.size
    val queue = java.util.ArrayDeque<Int>()
    var n : Int
    while (true) {
        n = dq.poll()
        if (n == e) {
            break
        }
        cnt++
        queue.add(n)
    }
    while (queue.isNotEmpty()) {
        dq.add(queue.poll())
    }
    return min(cnt, size - cnt)
}