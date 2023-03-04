package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/**
 *  연구소 3
 *  - 모든 빈 칸에 바이러스가 있게 되는 최소 시간
 *  - 바이러스
 *      - 활성화
 *      - 비활성화
 *
 *  - 4 <= N <= 20, 1 <= M <= 10
 *
 *  - 백 트래킹 + BFS
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val queue = java.util.ArrayDeque<Virus>()
private val virusList = mutableListOf<Virus>()
private lateinit var active: Array<Virus>
private lateinit var visited: Array<BooleanArray>

private class Virus(val x: Int, val y: Int, val t: Int)

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var answer = Integer.MAX_VALUE
private var emptyPlace = 0
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) { IntArray(n) }
    // 0 - 빈칸, 1 - 벽, 2 - 바이러스
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            val num = st.nextToken().toInt()
            arr[i][j] = num
            when (num) {
                2 -> virusList.add(Virus(j, i, 0))
                0 -> emptyPlace++
            }
        }
    }
    active = Array(m) { Virus(0, 0, 0) }
    setVirus(n, m, 0, 0)

    if (emptyPlace == 0) {
        println(0)
        return
    }

    if (answer == Integer.MAX_VALUE) {
        println(-1)
    } else {
        println(answer)
    }
}

fun setVirus(n: Int, m: Int, st: Int, cnt: Int) {
    if (cnt == m) {
        bfs17142(n)
        return
    }
    for (i in st until virusList.size) {
        active[cnt] = virusList[i]
        setVirus(n, m, i + 1, cnt + 1)
    }
}

fun bfs17142(n: Int) {
    queue.clear()
    visited = Array(n) { BooleanArray(n) }
    var emptyCnt = 0

    for (v in active) {
        queue.add(v)
        visited[v.y][v.x] = true
    }

    while (!queue.isEmpty()) {
        val q = queue.poll()
        for (i in 0 until 4) {
            val nx = q.x + dx[i]
            val ny = q.y + dy[i]
            if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue
            if (arr[ny][nx] == 1) continue
            if (visited[ny][nx]) continue

            if (arr[ny][nx] == 0) {
                emptyCnt++
            }

            if (emptyCnt == emptyPlace) {
                answer = min(answer, q.t + 1)
            }
            queue.add(Virus(nx, ny, q.t + 1))
            visited[ny][nx] = true
        }
    }
}