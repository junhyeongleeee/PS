package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
private val dy = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)

private val queue = ArrayDeque<Pair<Int, Int>>()

fun main() = with(System.`in`.bufferedReader()) {

    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    arr = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    var result = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (visited[i][j]) continue

            if (solve1245(i, j, n, m)) {
//                println("i: $i j: $j")
                result++
            }
        }
    }

    println(result)
}

fun solve1245(i: Int, j: Int, n: Int, m: Int): Boolean {

    queue.add(Pair(i, j))
    visited[i][j] = true
    var result = true

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        val x = q.second
        val y = q.first

        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
            if (arr[ny][nx] > arr[y][x]) {
                result = false
            }
            if (visited[ny][nx]) continue

            if (arr[ny][nx] == arr[y][x]) {
                queue.add(Pair(ny, nx))
                visited[ny][nx] = true
            }
        }
    }

    return result
}