package kotlinCode.boj

import java.util.LinkedList

/**
 *   첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다.
 *   다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고
 *   , 1은 벽을 의미한다.
 *
 *
 * 100* 100 = 10,000
 * n  m
 * 4  2
 * 0001
 * 1000
 *
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 */

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
var n: Int = 0
var m: Int = 0
private var arr: Array<IntArray> = arrayOf()
private var cntArr: Array<IntArray> = arrayOf()

data class Point(val x: Int, val y: Int, val dis: Int)

fun main() {

    val input = readln().split(" ").map { it.toInt() }
    n = input[0]
    m = input[1]

    arr = Array(m) { IntArray(n) }
    cntArr = Array(m) { IntArray(n) { Int.MAX_VALUE } }

    repeat(m) { i ->
        arr[i] = readln().map { it.code - 48 }.toIntArray()
    }
    bfs(0, 0)
    println(cntArr[m - 1][n - 1])
}

private fun bfs(x: Int, y: Int) {

    val queue: LinkedList<Point> = LinkedList()
    queue.add(Point(x, y, 0))
    cntArr[y][x] = 0

    while (!queue.isEmpty()) {
        val current = queue.poll()

        repeat(4) { i ->
            val nx = dx[i] + current.x
            val ny = dy[i] + current.y

            if (nx in 0 until n && ny in 0 until m) {
                if (cntArr[ny][nx] > current.dis + arr[ny][nx]) {
                    cntArr[ny][nx] = current.dis + arr[ny][nx]
                    queue.add(Point(nx, ny, cntArr[ny][nx]))
                }
            }
        }
    }
}