package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  달이 차오른다 가자.
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<CharArray>
private lateinit var visited: Array<Array<BooleanArray>>
private val queue = java.util.ArrayDeque<Point1194>()

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

private data class Point1194(val x: Int, val y: Int, val d: Int, val key: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) { CharArray(m) }
    visited = Array(n) { Array(m) { BooleanArray(64) } }

    repeat(n) { i ->
        val line = readLine()
        line.forEachIndexed { j, c ->
            arr[i][j] = c
            if (c == '0') {
                queue.add(Point1194(j, i, 0, 0))
                visited[i][j][0] = true
            }
        }
    }
    println(bfs1194(n, m))
}

fun bfs1194(n: Int, m: Int): Int {
    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (arr[q.y][q.x] == '1') {
            return q.d
        }

        for (i in 0 until 4) {
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
            val nc = arr[ny][nx]

            if (nc == '#') continue

            if (visited[ny][nx][q.key]) continue

            when (nc) {
                in 'a'..'f' -> {
                    val nKey = q.key or (1 shl (nc - 'a'))
                    if (visited[ny][nx][nKey]) continue
                    visited[ny][nx][q.key] = true
                    visited[ny][nx][nKey] = true
                    queue.add(Point1194(nx, ny, q.d + 1, nKey))
                }
                in 'A'..'F' -> {
                    if (q.key and (1 shl nc - 'A') == 0) continue
                    visited[ny][nx][q.key] = true
                    queue.add(Point1194(nx, ny, q.d + 1, q.key))
                }
                '.', '1', '0' -> {
                    visited[ny][nx][q.key] = true
                    queue.add(Point1194(nx, ny, q.d + 1, q.key))
                }
            }
        }
    }
    return -1
}