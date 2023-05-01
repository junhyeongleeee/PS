package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }
    var result = Integer.MIN_VALUE

    for (i in 0..100) {

        visited = Array(n) { BooleanArray(n) }

        var cnt = 0
        for (j in 0 until n) {
            for (k in 0 until n) {
                if (arr[j][k] <= i || visited[j][k]) continue
                solve2468(j, k, i, n)
                cnt++
            }
        }
        result = max(result, cnt)
    }

    println(result)
}
fun solve2468(r: Int, c: Int, h: Int, n: Int) {
    val queue = java.util.ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(c, r))
    visited[r][c] = true

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue
            if (visited[ny][nx]) continue
            if (arr[ny][nx] <= h) continue
            visited[ny][nx] = true
            queue.add(Pair(nx, ny))
        }
    }
}