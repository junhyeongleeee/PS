package kotlinCode.boj

/**
 *  벽 부수고 이동하기
 *
 *  1 <= N, M <= 1,000
 */

private val br = System.`in`.bufferedReader()
private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<Array<BooleanArray>>
private val queue = java.util.ArrayDeque<State>()

data class State(val x: Int, val y: Int, val cnt: Int, val total: Int)

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n) { IntArray(m) }
    visited = Array(n) { Array(m) { BooleanArray(2) } }

    repeat(n) { i ->
        val line = readLine()
        line.forEachIndexed { j, c ->
            arr[i][j] = c - '0'
        }
    }

    println(bfs2206(n, m))
}

fun bfs2206(n: Int, m: Int): Int {
    queue.add(State(0, 0, 1, 1))
    visited[0][0][1] = true
    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (q.x == m - 1 && q.y == n - 1) {
            return q.total
        }

        repeat(4) { i ->
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) return@repeat
            if (q.cnt == 1) {
                if (visited[ny][nx][q.cnt]) return@repeat
                val cnt = if (arr[ny][nx] == 1) 0 else 1
                queue.add(State(nx, ny, cnt, q.total + 1))
                visited[ny][nx][cnt] = true
            }
            else {
                if (arr[ny][nx] == 1) return@repeat
                if (visited[ny][nx][0]) return@repeat
                queue.add(State(nx, ny, 0, q.total + 1))
                visited[ny][nx][0] = true
            }
        }
    }
    return -1
}