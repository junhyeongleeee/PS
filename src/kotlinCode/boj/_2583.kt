package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    arr = Array(m) { IntArray(n) }
    visited = Array(m) { BooleanArray(n) }

    repeat(k) {
        st = StringTokenizer(readLine())

        val lx = st.nextToken().toInt()
        val ly = st.nextToken().toInt()
        val rx = st.nextToken().toInt()
        val ry = st.nextToken().toInt()

        for (i in (m - ry) until (m - ly)) {
            for (j in lx until rx) {
                arr[i][j] = 1
            }
        }
    }

    var answer = 0
    val areaList = mutableListOf<Int>()
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (visited[i][j] || arr[i][j] == 1) {
                continue
            }
            areaList.add(solve2583(j, i, m, n))
            answer++
        }
    }
    println(answer)
    println(areaList.sorted().joinToString(" "))
}
fun solve2583(c: Int, r: Int, m: Int, n: Int): Int {
    var result = 1
    val queue = java.util.ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(c, r))
    visited[r][c] = true

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1) continue
            if (visited[ny][nx] || arr[ny][nx] == 1) continue
            visited[ny][nx] = true
            result++
            queue.add(Pair(nx, ny))
        }
    }

    return result
}