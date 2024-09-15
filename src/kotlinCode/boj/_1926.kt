package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var visited: Array<BooleanArray>
private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var cnt = 0
    var answer = 0
    arr = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (visited[i][j] || arr[i][j] == 0) continue
            cnt++
            answer = max(answer, func1(i, j, n, m))
        }
    }

    println(cnt)
    println(answer)
}

fun func1(i: Int, j: Int, n: Int, m: Int): Int {

    val queue = ArrayDeque<Pair<Int, Int>>()
    visited[i][j] = true
    queue.add(i to j)
    var cnt = 1

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        val x = q.second
        val y = q.first

        for (k in 0 until 4) {
            val nx = x + dx[k]
            val ny = y + dy[k]

            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) continue
            if (arr[ny][nx] == 0 || visited[ny][nx]) continue

            visited[ny][nx] = true
            cnt++
            queue.add(ny to nx)
        }
    }

    return cnt
}