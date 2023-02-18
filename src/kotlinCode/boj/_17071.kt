package kotlinCode.boj

import java.util.*

/**
 *  숨바꼭질 5
 *
 *  0 <= N <= 500,000
 *  0 <= K <= 500,000
 */

private val br = System.`in`.bufferedReader()
private val ad = ArrayDeque<Int>()
private val visited = Array(2) { BooleanArray(500001) }
fun main() = with(br) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    if (n == k) {
        println(0)
        return@with
    }
    println(bfs17071(n, k))
}

fun bfs17071(s: Int, k: Int): Int {
    ad.add(s)
    visited[0][s] = true
    var mod = 0
    var time = 0
    var nk = k
    while (!ad.isEmpty()) {
        mod = ++time % 2
        nk += time

        if (nk > 500000) {
            return -1
        }

        for (i in 0 until ad.size) {
            val q = ad.poll()

            for (j in 0 until 3) {
                val ni = when(j) {
                    0 -> q * 2
                    1 -> q + 1
                    else -> q - 1
                }
                if (ni < 0 || ni > 500000 || visited[mod][ni]) continue
                ad.add(ni)
                visited[mod][ni] = true
            }
        }
        if (visited[mod][nk]) {
            return time
        }
    }
    return -1
}