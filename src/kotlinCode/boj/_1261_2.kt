package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer


/**
 *  keyword
 *      - n, m <= 100
 *      - 미로는 빈방, 벽 으로 이루어져있다.
 *      - 이동방식은 상화좌우 인접한 빈 방
 *      - 벽을 부술 수 있음
 *      - n, m 으로 최소 몇개의 벽을 부숴야 갈 수 있는지?
 *
 *  solution
 *      - 그래프 탐색
 *      - 탐색 시, 빈방과 벽 상관없이 탐색해야 함
 *      - 부순 벽을 위치마다 카운팅하고 기존 갯수와 비교하면서 탐색
 */

private lateinit var st: StringTokenizer

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

data class Point1261(val x: Int, val y: Int, val c: Int)

private lateinit var arr: Array<Array<IntArray>>
private lateinit var visited: Array<BooleanArray>

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n) { Array(m) { IntArray(2) { Int.MAX_VALUE } } }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        val line = readLine()
        line.forEachIndexed { j, c ->
            arr[i][j][0] = c - '0'
        }
    }

    println(bfs1261(n, m))
}

fun bfs1261(n: Int, m: Int): Int {
    val queue = ArrayDeque<Point1261>()
    visited[0][0] = true
    queue.add(Point1261(0, 0, 0))

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        for (i in 0 until 4) {
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]

            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) continue

            val v = arr[ny][nx][0]
            val c = arr[ny][nx][1]
            val nc = q.c + if (v == 1) 1 else 0


            if (c == nc && visited[ny][nx]) continue

            if (nc < c) {
                queue.add(Point1261(ny, nx, nc))
            }

            visited[ny][nx] = true
        }
    }

    return arr[n - 1][m - 1][0]
}