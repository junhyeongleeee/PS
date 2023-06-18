package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(-1, 0, 1, 0)
private var answer = 1

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    // d : 0 북, 1 동, 2 남, 3서
    val (r, c, d) = readLine().split(" ").map { it.toInt() }

    arr = Array(n) { IntArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    solve14503(r, c, d, n, m)

    println(answer)
}

fun solve14503(r: Int, c: Int, d: Int, n: Int, m: Int) {

    arr[r][c] = -1
    var vector = d

    for (i in 0 until 4) {
        vector = (vector + 3) % 4
        val ny = r + dy[vector]
        val nx = c + dx[vector]
        if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue
        if (arr[ny][nx] == 1 || arr[ny][nx] == - 1) continue
        answer++
        solve14503(ny, nx, vector, n, m)
        return
    }

    val nd = (vector + 2) % 4
    val ny = r + dy[nd]
    val nx = c + dx[nd]
    if (arr[ny][nx] == 1) {
        return
    }
    solve14503(ny, nx, vector, n, m)
}