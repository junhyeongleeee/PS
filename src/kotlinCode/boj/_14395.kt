package kotlinCode.boj

import java.util.ArrayDeque


fun main() = with(System.`in`.bufferedReader()){
    val (s, t) = readLine().split(" ").map { it.toInt() }

    val set = hashSetOf<Long>()

    val queue = ArrayDeque<Pair<Long, String>>()
    queue.add(Pair(s.toLong(), ""))

    if (s == t) {
        println(0)
        return@with
    }

    while (queue.isNotEmpty()) {

        val q = queue.poll()
        val n = q.first
        val s = q.second

        if (n == t.toLong()) {
            println(s)
            return@with
        }

        println("n: $n s: $s")

        if (n == 0L) continue

        // 곱
        var nn = n * n
        if (!set.contains(nn)) {
            queue.add(Pair(nn, "$s*"))
            set.add(nn)
        }

        // 합
        nn = n + n
        if (!set.contains(nn)) {
            queue.add(Pair(nn, "$s+"))
            set.add(nn)
        }

        // 차
        nn = n - n
        if (!set.contains(nn)) {
            queue.add(Pair(nn, "$s-"))
            set.add(nn)
        }

        // 나눗셈
        nn = n / n
        if (!set.contains(nn)) {
            queue.add(Pair(nn, "$s/"))
            set.add(nn)
        }
    }

    println(-1)
}