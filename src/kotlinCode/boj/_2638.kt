package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var airArr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val queue = java.util.ArrayDeque<Pair<Int, Int>>()

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    arr = Array(n) { IntArray(m) }

    var total = 0
    var result = 0

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            val v = st.nextToken().toInt()
            arr[i][j] = v
            if (v == 1) {
                total++
            }
        }
    }

    while (true) {

        if (total == 0) break

        airArr = Array(n) { IntArray(m) }
        visited = Array(n) { BooleanArray(m) }

        // 공기 나누기
        var air = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (visited[i][j] || arr[i][j] == 1) continue
                checkAir(i, j, n, m, air)
                air++
            }
        }

        val chList = mutableListOf<Pair<Int, Int>>()
        // 녹는 치즈 확인
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[i][j] == 0) continue

                var cnt = 0
                // 공기 확인
                for (k in 0 until 4) {
                    val nx = j + dx[k]
                    val ny = i + dy[k]

                    if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
                    if (airArr[ny][nx] != 0 || arr[ny][nx] == 1) continue

                    cnt++
                }
                if (cnt < 2) continue

                chList.add(Pair(j, i))
            }
        }

        chList.forEach {
            val y = it.second
            val x = it.first
            arr[y][x] = 0
            total--
        }

        result++
    }

    println(result)
}
fun checkAir(r: Int, c: Int, n: Int, m: Int, air: Int) {
    queue.add(Pair(c, r))
    visited[r][c] = true
    airArr[r][c] = air

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
            if (arr[ny][nx] == 1) continue
            if (visited[ny][nx]) continue

            visited[ny][nx] = true
            airArr[ny][nx] = air
            queue.add(Pair(nx, ny))
        }
    }
}