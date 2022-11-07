package kotlinCode.boj

import java.util.LinkedList

/**
 *  N X M 크기의 배열로 표현되는 미로가 있다.
 *   - 1은 이동할 수 있는 칸
 *   - 0은 이동할 수 없는 칸
 *   - (1,1) 에서 출발 (N, M) 위치로 이동할 때 지나야 하는 최소의 칸 수 구함.
 *
 *   - N, M (2 <= N,M <= 100) 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 *   - 항상 도착위치로 이동할 수 있느 경우만 입력으로 주어진다.
 *   - bfs
 *
 *     m  m m m m m m m
 *     n
 *     n
 *     n
 *     n
 *     n
 *
 */

data class Dot(val x: Int, val y: Int)

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var visited: Array<BooleanArray>
private lateinit var arr: Array<IntArray>
private lateinit var sum: Array<IntArray>
private val queue = LinkedList<Dot>()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { IntArray(m) }

    visited = Array(n) { BooleanArray(m) }
    repeat(n) { i ->
        val line = readLine()
        repeat(m) { j ->
            arr[i][j] = line[j] - '0'
        }
    }
    println(bfs(n, m))
}

private fun bfs(n: Int, m: Int): Int {
    queue.add(Dot(0, 0))
    visited[0][0] = true

    while (!queue.isEmpty()) {
        val n_d = queue.poll()

        repeat(4) {
            val nX = dx[it] + n_d.x
            val nY = dy[it] + n_d.y

            if (nX in 0 until m && nY in 0 until n) {
                if (nX == m - 1 && nY == n - 1) return arr[n_d.y][n_d.x] + 1

                if (!visited[nY][nX] && arr[nY][nX] != 0) {
                    queue.add(Dot(nX, nY))
                    arr[nY][nX] = arr[n_d.y][n_d.x] + 1
                    visited[nY][nX] = true
                }
            }
        }
    }

    return 0
}