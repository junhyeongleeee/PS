package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

/**
 *  모양 만들기
 *
 *  2 <= N, M <= 1,000
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)
private val queue = java.util.ArrayDeque<State16>()

data class State16(var x: Int, var y: Int)

private lateinit var visited: Array<BooleanArray>
fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = mutableMapOf<Int, Int>()
    arr = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            val v = st.nextToken().toInt()
            arr[i][j] = v
        }
    }

    var group = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            // i == y, j == x
            if (arr[i][j] == 1 && !visited[i][j]) {
                map[group + 1] = bfs16932(j, i, n, m, group)
                group++
            }
        }
    }

    var merge = Integer.MIN_VALUE
    for (i in 0 until n) {
        for (j in 0 until m) {
            // i == y, j == x
            if (arr[i][j] == 0) {
                val set = hashSetOf<Int>()
                repeat(4) {
                    val nx = j + dx[it]
                    val ny = i + dy[it]
                    if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) return@repeat
                    if (arr[ny][nx] == 0) return@repeat
                    set.add(arr[ny][nx])
                }
                var total = 1
                set.forEach { total += map.getOrDefault(it, 0) }
                merge = max(merge, total)
            }
        }
    }

    println(merge)
}

fun bfs16932(x: Int, y: Int, n: Int, m: Int, group: Int): Int {
    var cnt = 1
    queue.add(State16(x, y))
    visited[y][x] = true
    arr[y][x] += group
    while (!queue.isEmpty()) {
        val q = queue.poll()

        repeat(4) { i ->
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) return@repeat
            if (visited[ny][nx]) return@repeat
            if (arr[ny][nx] == 1) {
                arr[ny][nx] += group
                visited[ny][nx] = true
                queue.add(State16(nx, ny))
                cnt++
            }
        }
    }
    return cnt
}