package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  벽 부수고 이동하기 2
 *  - 0 은 이동할 수 있음
 *  - 1 은 이동할 수 없음
 *  - 최단경로 : 시작 끝 포함
 *  - 벽을 부술 수 있음 K개 까지
 *
 *  - 1 <= N,M <= 1,000
 *  1 <= K <= 10
 */
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val queue = java.util.ArrayDeque<Point14>()
private lateinit var visited: Array<Array<BooleanArray>>

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

data class Point14(val x: Int, val y: Int, val k: Int, val total: Int)

fun main() = with(br) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    arr = Array(n) { IntArray(m) }
    visited = Array(n) { Array(m) { BooleanArray(k + 1) } }

    repeat(n) { i ->
        val input = readLine()
        input.forEachIndexed { j, c ->
            arr[i][j] = c - '0'
        }
    }

    println(bfs14442(n, m, k))
}

fun bfs14442(n: Int, m: Int, k: Int): Int {
    queue.add(Point14(0, 0, 0, 1))
    visited[0][0][0] = true
    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (q.x == m - 1 && q.y == n - 1) {
            return q.total
        }

        for (i in 0 until 4) {
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
            if (visited[ny][nx][q.k]) continue
            if (arr[ny][nx] == 1) {
                if (q.k == k) continue
                visited[ny][nx][q.k + 1] = true
                queue.add(Point14(nx, ny, q.k + 1, q.total + 1))
            }
            else {
                visited[ny][nx][q.k] = true
                queue.add(Point14(nx, ny, q.k, q.total + 1))
            }
        }
    }
    return -1
}