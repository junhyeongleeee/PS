package kotlinCode.boj

import kotlin.math.max

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var arr: Array<CharArray>
data class Treasure(val x: Int, val y: Int, val d: Int)
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    // L : 육지 W: 바다
    // n , m <= 50

    arr = Array(n) { CharArray(m) }
    repeat(n) { i ->
        readLine().forEachIndexed { j, c ->
            arr[i][j] = c
        }
    }

    var result = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (arr[i][j] == 'W') continue
            result = max(result, solve2589(j, i, n, m))
        }
    }
    println(result)
}

fun solve2589(sx: Int, sy: Int, n: Int, m: Int): Int {
    val queue = java.util.ArrayDeque<Treasure>()
    val visited = Array(n) { BooleanArray(m) }
    queue.add(Treasure(sx, sy, 0))
    visited[sy][sx] = true
    var result = 0

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.x
        val y = q.y
        val d = q.d

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
            if (arr[ny][nx] == 'W') continue
            if (visited[ny][nx]) continue

            visited[ny][nx] = true
            queue.add(Treasure(nx, ny, d + 1))
            result = max(result, d + 1)
        }
    }
    return result
}