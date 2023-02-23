package kotlinCode.boj

import java.util.*

/**
 *  말이 되고픈 원숭이
 *
 *  1 <= W, H <= 200
 *  0 <= K <= 30
 */

private val br = System.`in`.bufferedReader()
private lateinit var arr: Array<IntArray>
private val hx = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
private val hy = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private val queue = ArrayDeque<MonKey>()
private lateinit var visited: Array<Array<BooleanArray>>
private lateinit var st: StringTokenizer

data class MonKey(val x: Int, val y: Int, val k: Int, val cnt: Int)

fun main() = with(br) {
    val k = readLine().toInt()
    val (w, h) = readLine().split(" ").map { it.toInt() }

    arr = Array(h) { IntArray(w) }
    visited = Array(h) { Array(w) { BooleanArray(31) } }
    repeat(h) { i ->
        st = StringTokenizer(readLine())
        repeat(w) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    println(bfs1600(k, w, h))

    return@with
}

fun bfs1600(k: Int, w: Int, h: Int): Int {
    queue.add(MonKey(0, 0, k, 0))
    visited[0][0][k] = true
    while (!queue.isEmpty()) {
        val q = queue.poll()
        if (q.x == w - 1 && q.y == h - 1) {
            return q.cnt
        }

        if (q.k > 0) {
            for (i in 0 until 8) {
                val nx = q.x + hx[i]
                val ny = q.y + hy[i]
                if (!check(nx, ny, q.k - 1, w, h)) continue
                visited[ny][nx][q.k - 1] = true
                queue.add(MonKey(nx, ny, q.k - 1, q.cnt + 1))
            }
        }
        for (i in 0 until 4) {
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (!check(nx, ny, q.k, w, h)) continue
            visited[ny][nx][q.k] = true
            queue.add(MonKey(nx, ny, q.k, q.cnt + 1))
        }
    }
    return -1
}

fun check(nx: Int, ny: Int, k: Int, w: Int, h: Int): Boolean {
    if (nx < 0 || nx > w - 1 || ny < 0 || ny > h - 1 || visited[ny][nx][k]) return false
    if (arr[ny][nx] == 1) return false
    return true
}