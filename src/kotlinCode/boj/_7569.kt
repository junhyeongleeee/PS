package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.max

/**
 *   토마토는 격자 모양 상자의 칸에 하나씩 넣은 다음,
 *   상자들을 수직으로 쌓아 올려서 창고에 보관한다.
 *
 *   하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향 이다.
 *
 *   토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과
 *   익지 않은 토마토들의 정보가 주어졌을 때,
 *   며칠이 지나면 토마토들이 모두 익는지, 최소 일수를 구하라
 *
 *   2<= N, M, H <= 100
 *
 *   1 -> 익은 토마토, -1 -> 토마토가 들어 있지 않은 칸
 */

private val br = System.`in`.bufferedReader()
private val dx = intArrayOf(-1, 1, 0, 0, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1, 0, 0)
private val dz = intArrayOf(0, 0, 0, 0, 1, -1)
private lateinit var st: StringTokenizer
private lateinit var queue: LinkedList<Triple<Int, Int, Int>>
private lateinit var box: Array<Array<IntArray>>

fun main() = with(br) {
    val (m, n, h) = readLine().split(" ").map { it.toInt() }

    box = Array(h) { Array(n) { IntArray(m) } }
    queue = LinkedList()

    repeat(h) { i ->
        repeat(n) { j ->
            st = StringTokenizer(readLine())
            repeat(m) { k ->
                box[i][j][k] = st.nextToken().toInt()
                if (box[i][j][k] == 1) {
                    queue.add(Triple(k, j, i))
                }
            }
        }
    }

    bfs(m, n, h)

    var max = 0

    box.forEach { h ->
        h.forEach { n ->
            n.forEach {
                if (it == 0) {
                    println(-1)
                    return@with
                }
                max = max(max, it)
            }
        }
    }

    println(max - 1)
}

fun bfs(m: Int, n: Int, h: Int) {
    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second
        val z = q.third
        repeat(6) { i ->
            val nx = dx[i] + x
            val ny = dy[i] + y
            val nz = dz[i] + z

            if (nx in 0 until m &&
                ny in 0 until n &&
                nz in 0 until h
            ) {
                if (box[nz][ny][nx] == 0) {
                    box[nz][ny][nx] = box[z][y][x] + 1
                    queue.add(Triple(nx, ny, nz))
                }
            }
        }
    }
}