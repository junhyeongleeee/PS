package kotlinCode.boj

import java.util.ArrayDeque


private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    val deque = ArrayDeque<Int>()
    (1..n).forEach { deque.add(it) }

    sb.append("<")
    var c = 1
    while (deque.isNotEmpty()) {
        val v = deque.poll()
        if (c == k) {
            sb.append(if (deque.isEmpty()) { "$v>" } else { "$v, " })
            c = 0
        } else {
            deque.add(v)
        }
        c++
    }

    println(sb)
}