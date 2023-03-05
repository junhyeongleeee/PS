package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  빙산
 *
 *  3 <= n, m <= 300
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val queue = java.util.ArrayDeque<Iceberg>()
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

data class Iceberg(val x: Int, val y: Int, val h: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            val n = st.nextToken().toInt()
            arr[i][j] = n
            if (n != 0) {
                queue.add(Iceberg(j, i, n))
            }
        }
    }
    var result = 0
    var time = 0
    while (true) {
        var group = 0
        visited = Array(n) { BooleanArray(m) }

        if (queue.size == 0) {
            println(0)
            break
        }

        // 덩어리 개수 세기 , poll 할 필요가 없음.
        for (q in queue) {
            if (!visited[q.y][q.x]) {
                group++
                dfs2573(q, n, m)
            }
        }

        if (group >= 2) {
            println(time)
            break
        }

        // 빙산 녹이기
        val dis = mutableListOf<Iceberg>()
        for (i in 0 until queue.size) {
            val q = queue.poll()
            var sea = 0
            for (j in 0 until 4) {
                val nx = q.x + dx[j]
                val ny = q.y + dy[j]
                if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
                if (arr[ny][nx] == 0) {
                    sea++
                }
            }
            val nq = if (arr[q.y][q.x] <= sea) {
                Iceberg(q.x, q.y, 0)
            } else {
                queue.add(Iceberg(q.x, q.y, q.h - sea))
                Iceberg(q.x, q.y, q.h - sea)
            }
            dis.add(nq)
        }

        dis.forEach {
            arr[it.y][it.x] = it.h
        }

        time++
    }
}

fun dfs2573(iceberg: Iceberg, n: Int, m: Int) {
    for (i in 0 until 4) {
        val nx = iceberg.x + dx[i]
        val ny = iceberg.y + dy[i]
        if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
        if (visited[ny][nx] || arr[ny][nx] == 0) continue
        visited[ny][nx] = true
        dfs2573(Iceberg(nx, ny, iceberg.h), n, m)
    }
}