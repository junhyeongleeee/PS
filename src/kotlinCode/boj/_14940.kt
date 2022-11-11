package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer

/**
 *  지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라
 *  문제를 쉽게 만듫기 위해 가로와 세로로만 움직일 수 있다고 한다.
 *
 *  지도의 크기 n, m
 *   - (2<= n, m <= 1000)
 *
 *   0은 갈 수 없는 땅, 1은 갈 수 있는 땅, 2는 목표 지점
 *   원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1 을 출력
 *
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var visited: Array<BooleanArray>
private lateinit var result: Array<IntArray>
private lateinit var st: StringTokenizer
private lateinit var arrivePoint: Pair<Int, Int>

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val arr = Array(n) { IntArray(m) }
    result = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
            if (arr[i][j] == 2) {
                arrivePoint = Pair(j, i)
            }
        }
    }

    bfs(arrivePoint, n, m, arr)

    repeat(n) { i ->
        repeat(m) back@{ j ->
            if (result[i][j] == 0 && arr[i][j] == 1) {
                repeat(4) {
                    val nx = dx[it] + i
                    val ny = dy[it] + j
                    if (nx in 0 until m && ny in 0 until n) {
                        if (result[ny][nx] == 0 && arr[i][j] == 2) {
                            return@back
                        }
                    }
                }
                result[i][j] = -1
            }
        }
    }

    result.forEach { println(it.joinToString(" ")) }
}

fun bfs(sp: Pair<Int, Int>, n: Int, m: Int, arr: Array<IntArray>) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(sp)
    visited[sp.second][sp.first] = true

    while (!queue.isEmpty()) {
        val q = queue.poll()

        repeat(4) {
            val nx = dx[it] + q.first
            val ny = dy[it] + q.second

            if (nx in 0 until m && ny in 0 until n) {
                if (!visited[ny][nx] && arr[ny][nx] == 1) {
                    visited[ny][nx] = true
                    result[ny][nx] = result[q.second][q.first] + 1
                    queue.add(Pair(nx, ny))
                }
            }
        }
    }
}