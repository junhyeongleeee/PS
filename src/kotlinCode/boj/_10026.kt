package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  적록 색약
 *
 *  R, G, B
 */
private lateinit var st: StringTokenizer
private lateinit var weakness: Array<IntArray>
private lateinit var normal: Array<IntArray>
private lateinit var visited1: Array<BooleanArray>
private lateinit var visited2: Array<BooleanArray>
private val queue = java.util.ArrayDeque<Pair<Int, Int>>()

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    weakness = Array(n) { IntArray(n) }
    normal = Array(n) { IntArray(n) }
    visited1 = Array(n) { BooleanArray(n) }
    visited2 = Array(n) { BooleanArray(n) }

    repeat(n) { i ->
        val input = readLine()
        input.forEachIndexed { j, c ->
            when (c) {
                'R' -> {
                    weakness[j][i] = 0
                    normal[j][i] = 0
                }
                'G' -> {
                    weakness[j][i] = 0
                    normal[j][i] = 1
                }
                'B' -> {
                    weakness[j][i] = 1
                    normal[j][i] = 2
                }
            }
        }
    }
    var weakCnt = 0
    var normalCnt = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!visited1[i][j]) {
                weakCnt++
                bfs10026(n, Pair(j, i), visited1, weakness)
            }
            if (!visited2[i][j]) {
                normalCnt++
                bfs10026(n, Pair(j, i), visited2, normal)
            }
        }
    }
    println("$normalCnt $weakCnt")
}

fun bfs10026(n: Int, p: Pair<Int, Int>, visited: Array<BooleanArray>, arr: Array<IntArray>) {
    queue.clear()
    queue.add(p)
    visited[p.second][p.first] = true

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue
            // 색약
            if (!visited[ny][nx] && arr[ny][nx] == arr[y][x]) {
                queue.add(Pair(nx, ny))
                visited[ny][nx] = true
            }
        }
    }
}